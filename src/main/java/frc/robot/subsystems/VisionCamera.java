package frc.robot.subsystems;

import java.util.List;
import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.VisionConstants.*;

public class VisionCamera extends SubsystemBase {

    String s_cameraName;

    PhotonCamera v_camera;
    Field2d m_field;
    PhotonPoseEstimator v_estimator;
    Pose3d p_pose;

    private Matrix<N3, N1> curStdDevs;

    private final Sendable m_fieldSendable = new Sendable() {
        @Override
        public void initSendable(SendableBuilder builder) {
            m_field.initSendable(builder);
            m_field.setRobotPose(p_pose.toPose2d());
        };
    };   

    public VisionCamera(String name) {
        s_cameraName = name;
        v_camera = new PhotonCamera(s_cameraName);

        m_field = new Field2d();
        p_pose  = new Pose3d();

        v_estimator = new PhotonPoseEstimator(kTagLayout, kRobotToCam);
    }

    private void takePicture() {
        v_camera.setLED(VisionLEDMode.kBlink);
        v_camera.takeInputSnapshot();
        v_camera.setLED(VisionLEDMode.kDefault);
    }

    /**
     * @return a {@link Command} that uses the
     *         {@link PhotonCamera#takeInputSnapshot()}
     *         method to take a screenshot
     */
    public Command screenshot() {
        return run(
                () -> takePicture());
    }

    @Override
    public void periodic() {
    Optional<EstimatedRobotPose> visionEst = Optional.empty();
        for (var result : v_camera.getAllUnreadResults()) {
            visionEst = v_estimator.estimateCoprocMultiTagPose(result);
            if (visionEst.isEmpty()) {
                visionEst = v_estimator.estimateLowestAmbiguityPose(result);
            }
            updateEstimationStdDevs(visionEst, result.getTargets());

            visionEst.ifPresent(
                est -> {
                    // Change our trust in the measurement based on the tags we can see
                    // var estStdDevs = getEstimationStdDevs();

                    /* 
                     * This estConsumer stuff is meant for an actual swerve drive.
                     * I'm considering moving vision development to the TestDrive
                     * because I don't want to mess with the estConsumer junk
                     * without a swerve drive.
                     */
                    // estConsumer.accept(est.estimatedPose.toPose2d(), est.timestampSeconds, estStdDevs);
                    m_field.setRobotPose(est.estimatedPose.toPose2d());
                        
                }
            );
        }
        SmartDashboard.putData(s_cameraName + " Field2d", m_fieldSendable);
    }

    /**
     * Calculates new standard deviations This algorithm is a heuristic that creates dynamic standard
     * deviations based on number of tags, estimation strategy, and distance from the tags.
     *
     * @param estimatedPose The estimated pose to guess standard deviations for.
     * @param targets All targets in this camera frame
     * @author PhotonVision
     */
    private void updateEstimationStdDevs(
            Optional<EstimatedRobotPose> estimatedPose, List<PhotonTrackedTarget> targets) {
        if (estimatedPose.isEmpty()) {
            // No pose input. Default to single-tag std devs
            curStdDevs = kSingleTagStdDevs;

        } else {
            // Pose present. Start running Heuristic
            var estStdDevs = kSingleTagStdDevs;
            int numTags = 0;
            double avgDist = 0;

            // Precalculation - see how many tags we found, and calculate an average-distance metric
            for (var tgt : targets) {
                var tagPose = v_estimator.getFieldTags().getTagPose(tgt.getFiducialId());
                if (tagPose.isEmpty()) continue;
                numTags++;
                avgDist +=
                        tagPose
                                .get()
                                .toPose2d()
                                .getTranslation()
                                .getDistance(estimatedPose.get().estimatedPose.toPose2d().getTranslation());
            }

            if (numTags == 0) {
                // No tags visible. Default to single-tag std devs
                curStdDevs = kSingleTagStdDevs;
            } else {
                // One or more tags visible, run the full heuristic.
                avgDist /= numTags;
                // Decrease std devs if multiple targets are visible
                if (numTags > 1) estStdDevs = kMultiTagStdDevs;
                // Increase std devs based on (average) distance
                if (numTags == 1 && avgDist > 4)
                    estStdDevs = VecBuilder.fill(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
                else estStdDevs = estStdDevs.times(1 + (avgDist * avgDist / 30));
                curStdDevs = estStdDevs;
            }
        }
    }

    /**
     * Returns the latest standard deviations of the estimated pose from {@link
     * #getEstimatedGlobalPose()}, for use with {@link
     * edu.wpi.first.math.estimator.SwerveDrivePoseEstimator SwerveDrivePoseEstimator}. This should
     * only be used when there are targets visible.
     *
     * @author PhotonVision
     */
    public Matrix<N3, N1> getEstimationStdDevs() {
        return curStdDevs;
    }

    @FunctionalInterface
    public static interface EstimateConsumer {
        public void accept(Pose2d pose, double timestamp, Matrix<N3, N1> estimationStdDevs);
    }
}
