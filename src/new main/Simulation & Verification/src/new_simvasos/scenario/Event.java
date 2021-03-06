package new_simvasos.scenario;

import new_simvasos.timebound.TimeBound;

public class Event {

  public Action action;
  TimeBound timebound;
  public Event(Action action, TimeBound timebound) {  //constructor
    this.action = action;
    this.timebound = timebound;
  }

  public Action occur(int current) {
    if (this.timebound.getTick() == current) {
      this.action.behave();

      // return constant timebound because simulation log only need the time(tick) when the event was occurred.
      //ConstantTimeBound csnt_tb = new ConstantTimeBound(current);

      return this.action;
    }

    return null;
  }

  protected void reset() {
    this.timebound.reset();
  }
}
