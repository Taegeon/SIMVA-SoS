package new_simvasos.property;

import javafx.util.Pair;
import new_simvasos.log.Log;
import new_simvasos.log.SimulationLog;
import new_simvasos.log.Snapshot;
import new_simvasos.property.pattern.ExistenceChecker;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MCIPropertyChecker extends ExistenceChecker {

  public MCIPropertyChecker() {

    super();
  }

  // In Simulation Log, this property function check the simulation log
  // (rescued people number, patient number)
  public boolean check(SimulationLog simuLog, Property verificationProperty) {

    Pair<ArrayList<Integer>, ArrayList<Integer>> propertyLogs = simuLog.getPropertyCheckLogs();
    ArrayList<Integer> rescued = propertyLogs.getKey();
    ArrayList<Integer> environment = propertyLogs.getValue();
    
    double sumRescued = 0;
    double sumPatients = 0;
    
    for (int i : rescued ) {
      sumRescued += i;
    }
    
    for (int i : environment) {
      sumPatients += i;
    }
    
    sumPatients += sumRescued;
    if((sumRescued / sumPatients) > verificationProperty.getValue()) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  protected boolean evaluateState(Snapshot snapshot, Property verificationProperty) {
    StringTokenizer st = new StringTokenizer(snapshot.getSnapshotString(), " ");
    while(st.hasMoreTokens()) {
      if(st.nextToken().equals("RescuedRate:"))
        break;
    }


    double rescueRate = Double.parseDouble(st.nextToken());

    if(rescueRate > verificationProperty.getValue()){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  public boolean check(Log log, Property verificationProperty, int until) {
    return false;
  }

  @Override
  public boolean check(Log log, Property verificationProperty, double prob, int T) {
    return false;
  }

  @Override
  public boolean check(Log log, Property verificationProperty, double prob, int t, int T) {
    return false;
  }
}
