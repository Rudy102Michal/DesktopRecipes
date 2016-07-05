package pl.edu.pk.inf.java.DataClasses;

/**
 * Created by Michał on 2016-06-15.
 */
public enum Unit {
    GRAM,
    DG,
    KG,
    LITER,
    ML,
    SPOON,
    GLASS,
    PACK,
    AMOUNT,
    UNKN;


    @Override
    public String toString() {

        String ret;
        switch(this) {
            case GRAM:
                ret = "gram";
                break;
            case DG:
                ret = "dekagram";
                break;
            case KG:
                ret = "kilogram";
                break;
            case LITER:
                ret = "litr";
                break;
            case ML:
                ret = "mililitr";
                break;
            case SPOON:
                ret = "łyżeczka";
                break;
            case GLASS:
                ret = "szklanka";
                break;
            case PACK:
                ret = "paczka";
                break;
            case AMOUNT:
                ret = "sztuk";
                break;
            default:
                ret = "unknown";
                break;
        }
        return ret;
    }

    public Unit convertUnit(String strUnit)
    {
        for(Unit u : Unit.values())
        {
            if(strUnit.equals(u.toString()))
                return u;
        }

        return Unit.UNKN;
    }
}
