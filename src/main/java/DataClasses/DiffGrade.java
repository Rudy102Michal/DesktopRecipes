package DataClasses;

/**
 * Created by Michał on 2016-06-15.
 */
public enum DiffGrade {
    BASIC,
    EASY,
    MEDIUM,
    HARD,
    CHIEF_LIKE,
    GOD_TIER;

    @Override
    public String toString() {
        String ret;
        switch(this) {
            case BASIC:
                ret = "Bardzo łatwy";
                break;
            case EASY:
                ret = "Łatwy";
                break;
            case MEDIUM:
                ret = "Średni";
                break;
            case HARD:
                ret = "Trudny";
                break;
            case CHIEF_LIKE:
                ret = "MasterChef";
                break;
            default:
                ret = "unknown";
                break;
        }
        return ret;
    }
}
