package frc.robot;

import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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

    public Trigger strumBar(double threshold, EventLoop loop) {
        return axisGreaterThan(Axis.kStrumBar.value, threshold, loop);
    }

    public Trigger strumBar(double threshold) {
        return strumBar(threshold, CommandScheduler.getInstance().getDefaultButtonLoop());
    }
    
    public Trigger strumBar() {
        return strumBar(0.5);
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
