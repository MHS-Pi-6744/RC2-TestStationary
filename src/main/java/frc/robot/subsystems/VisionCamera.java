package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionCamera extends SubsystemBase {

    String s_cameraName;
    
    PhotonPipelineResult result;
    PhotonCamera v_camera;
    
    public VisionCamera(String name) {
        s_cameraName = name;
        v_camera = new PhotonCamera(s_cameraName);
    }

    private void takePicture() {
        v_camera.setLED(VisionLEDMode.kBlink);
        v_camera.takeInputSnapshot();
        v_camera.setLED(VisionLEDMode.kDefault);
    }

    /**
     * @return a {@link Command} that uses the
     * {@link PhotonCamera#takeInputSnapshot()}
     * method to take a screenshot
     */
    public Command screenshot() {
        return run(
            () -> takePicture()
        );
    }

    @Override
    public void periodic() {
        result = v_camera.getLatestResult();
        int id = -1;
        double yaw = -1;
        double pitch = -1;
        double skew = -1;

        SmartDashboard.putBoolean(s_cameraName + " Is Connected?", v_camera.isConnected());
        SmartDashboard.putBoolean(s_cameraName + " Has Targets?", result.hasTargets());
        if (result.hasTargets()) {
            PhotonTrackedTarget bestTarget = result.getBestTarget();
            id = bestTarget.getFiducialId();
            yaw = bestTarget.getYaw();
            pitch = bestTarget.getPitch();
            skew = bestTarget.getSkew();
        }
        SmartDashboard.putNumber(s_cameraName + " Best Target", id);
        SmartDashboard.putNumber(s_cameraName + " Best Target Yaw", yaw);
        SmartDashboard.putNumber(s_cameraName + " Best Target Pitch", pitch);
        SmartDashboard.putNumber(s_cameraName + " Best Target Skew", skew);
    }
}
