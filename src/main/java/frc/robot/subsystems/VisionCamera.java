package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonPipelineResult;

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
    }
}
