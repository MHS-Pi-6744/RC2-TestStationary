package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonUtils;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;

public class VisionCamera extends SubsystemBase {

    String s_cameraName;

    PhotonPipelineResult v_result;
    PhotonCamera v_camera;
    Field2d m_field;
    PhotonPoseEstimator v_estimator;
    Optional<EstimatedRobotPose> v_estimate;
    Pose3d p_pose;

    public static final Transform3d kRobotToCam = new Transform3d(
        new Translation3d(0.5, 0.0, 0.5),
        new Rotation3d(0, 0, 0)
    );

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

        v_estimator = new PhotonPoseEstimator(VisionConstants.kTagLayout, kRobotToCam);
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
        v_result = v_camera.getLatestResult();

        var target = v_result.getBestTarget();
        if (target != null) {
            if (VisionConstants.kTagLayout.getTagPose(target.getFiducialId()).isPresent()) {
                p_pose = PhotonUtils.estimateFieldToRobotAprilTag(
                    target.getBestCameraToTarget(),
                    VisionConstants.kTagLayout.getTagPose(target.getFiducialId()).get(),
                    kRobotToCam
                );
            }
        }
        m_field.setRobotPose(p_pose.toPose2d());
        SmartDashboard.putData(s_cameraName + " Field2d", m_fieldSendable);
    }
}
