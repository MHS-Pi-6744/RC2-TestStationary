# RC2 Implement Requirements
## Intake
### Physical
- 1 roller motor / SparkMax
- 1 pivot motor / SparkMax
### Function
- Pivot angle control, 2 position, 
    - trapezoidal control
    - 2 button control - press to initiate movement to position
- Roller speed control - 1 button press to switch from stopped to rolling & back?
- Autonomous control?
## Feeder
### Physical
- 1 NEO motor / SparkMax
### Function
## Shooter
### Physical
- 3 motors driving 3 flywheels - NEO Vortex / SparkFlex
- Each drivewheel is controlled independently
- All run at same shooting speed 
### Function
- 3 shooters. Numbers 1, 2 & 3 Each shooter enters shoot mode on command from ?
    - Prototype commands from 
- Provide a method that can be called by the feeder to determine if the flywheel is at shoot speed
- Speeds set as % of maximum motor speed
## Climber
### Physical
- 1 NEO 2.0? / SparkMax
- May need absolute encoder to calibrate
- May need range finder or limit switches to hold in raised position
### Function
- 2 position control using trapezoidal control
- Provides method to request position
## Driver Inputs-Commands
### Driver Controllers
### Button Box?
A potential addition to driver station - here is where to describe buttons that may be needed.
- 3 motor buttons enabling shooters 
## Autonomous Commands
