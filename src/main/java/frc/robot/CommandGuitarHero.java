package frc.robot;

import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * A fully self-contained class for using the guitar that came with
 * Guitar Hero: Live for the PS3.
 * 
 * @apiNote Only supports Command-Based operation
 * @author MattheDev53
 */
public class CommandGuitarHero extends CommandGenericHID {
    public enum Button {
        kTopWhite(1),
        kTopBlack(2),
        kMidBlack(3),
        kLowBlack(4),
        kMidWhite(5),
        kLowWhite(6),
        kHeroPower(9),
        kPause(10),
        kDiamond(11),
        kRestart(13);

        public final int value;

        Button(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            // Remove leading `k`
            return this.name().substring(1) + "Button";
        }
    }

    public enum Axis {
        kStrumBar(1),
        kTilt(2),
        kWhammyBar(3);

        public final int value;

        Axis(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            var name = this.name().substring(1); // Remove leading `k`
            return name;
        }
    }

    /**
     * Construct an instance of a controller.
     *
     * @param port The port index on the Driver Station that the controller is
     *             plugged into.
     */
    public CommandGuitarHero(int port) {
        super(port);
    }

    public Trigger topWhite(EventLoop loop) {
        return button(Button.kTopWhite.value, loop);
    }

    public Trigger topWhite() {
        return topWhite(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger midWhite(EventLoop loop) {
        return button(Button.kMidWhite.value, loop);
    }

    public Trigger midWhite() {
        return midWhite(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger lowWhite(EventLoop loop) {
        return button(Button.kMidWhite.value, loop);
    }

    public Trigger lowWhite() {
        return lowWhite(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger topBlack(EventLoop loop) {
        return button(Button.kTopBlack.value, loop);
    }

    public Trigger topBlack() {
        return topBlack(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger midBlack(EventLoop loop) {
        return button(Button.kMidBlack.value, loop);
    }

    public Trigger midBlack() {
        return midBlack(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger lowBlack(EventLoop loop) {
        return button(Button.kMidBlack.value, loop);
    }

    public Trigger lowBlack() {
        return lowBlack(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger heroPower(EventLoop loop) {
        return button(Button.kHeroPower.value, loop);
    }

    public Trigger heroPower() {
        return heroPower(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger pause(EventLoop loop) {
        return button(Button.kPause.value, loop);
    }

    public Trigger pause() {
        return pause(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger diamond(EventLoop loop) {
        return button(Button.kDiamond.value, loop);
    }

    public Trigger diamond() {
        return diamond(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger restart(EventLoop loop) {
        return button(Button.kRestart.value, loop);
    }

    public Trigger restart() {
        return restart(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    // Axis

    public Trigger tilt(double threshold, EventLoop loop) {
        return threshold > 0 ? axisGreaterThan(Axis.kTilt.value, threshold, loop)
                : axisLessThan(Axis.kTilt.value, threshold, loop);
    }

    public Trigger tilt(double threshold) {
        return tilt(threshold, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger tiltUp(EventLoop loop) {
        return tilt(-0.5, loop);
    }

    public Trigger tiltUp() {
        return tiltUp(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger tiltDown(EventLoop loop) {
        return tilt(0.5, loop);
    }

    public Trigger tiltDown() {
        return tiltDown(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public double getTilt() {
        return getRawAxis(Axis.kTilt.value);
    }

    public Trigger strumBar(double threshold, EventLoop loop) {
        return threshold > 0 ? axisGreaterThan(Axis.kStrumBar.value, threshold, loop)
                : axisLessThan(Axis.kStrumBar.value, threshold, loop);
    }

    public Trigger strumBar(double threshold) {
        return strumBar(threshold, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger strumUp(EventLoop loop) {
        return strumBar(-0.5, loop);
    }

    public Trigger strumUp() {
        return strumUp(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger strumDown(EventLoop loop) {
        return strumBar(0.5, loop);
    }

    public Trigger strumDown() {
        return strumDown(CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public double getStrumBar() {
        return getRawAxis(Axis.kStrumBar.value);
    }

    public Trigger whammyBar(double threshold, EventLoop loop) {
        return axisGreaterThan(Axis.kWhammyBar.value, threshold, loop);
    }

    public Trigger whammyBar(double threshold) {
        return whammyBar(threshold, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger whammyBar() {
        return whammyBar(0.5);
    }

    public double getWhammyBar() {
        return getRawAxis(Axis.kWhammyBar.value);
    }
}
