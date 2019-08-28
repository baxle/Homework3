package Company;

public class Currency
{
    private String code;

    private String name_full;

    private String id;

    private String name_short;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getname_full ()
    {
        return name_full;
    }

    public void setnameFull (String name_full)
    {
        this.name_full = name_full;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getname_short ()
    {
        return name_short;
    }

    public void setname_short (String name_short)
    {
        this.name_short = name_short;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", name_full = "+name_full+", id = "+id+", name_short = "+name_short+"]";
    }
}
