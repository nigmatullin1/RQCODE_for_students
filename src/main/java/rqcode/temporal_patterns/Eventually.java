package rqcode.temporal_patterns;



import rqcode.concepts.Checkable;

/**
 * Temporal requirements pattern:
 * P always eventually holds
 */

public class Eventually extends MonitoringLoop {
    
    Checkable p;

    
    public  Eventually(Checkable p) {
        this.p = p;

        this.setStatement(this.toString());
    }

    
    @Override
    public boolean exitCondition() {
        return (p.check() == CheckStatus.PASS);
    }

    
    @Override
    public boolean postcondition() {
        return (p.check() == CheckStatus.PASS);
    }

    
    @Override
    public String toString() {
        return "P always eventually holds, where P is:\n\n\t\t" +
                p.toString().replaceAll("\n", "\n\t\t");
    }

    
    @Override
    public String TCTL() {
        String pStr;
        if (p instanceof MonitoringLoop) {
            pStr = ((MonitoringLoop) p).TCTL();
        } else {
            pStr = p.getClass().getSimpleName();
        }
        return "AF (" + pStr + ")";
    }

}
