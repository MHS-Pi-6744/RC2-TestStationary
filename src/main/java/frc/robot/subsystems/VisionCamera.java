package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionCamera extends SubsystemBase {
    
    String s_cameraName;

    PhotonCamera v_camera;

    public VisionCamera(String name) {
        s_cameraName = name;
        v_camera = new PhotonCamera(s_cameraName);
    }

    @Override
    public void periodic() {
        var data = v_camera.getLatestResult();

        SmartDashboard.putBoolean(s_cameraName + " Has Targets?", data.hasTargets());
    }
}
