package Project.model;

public class Information
{
    private boolean wasAttended;
    private String nodeName;

    Information()
    {
        wasAttended = false;
        nodeName = "";
    }

    public String getNodeName()
    {
        return nodeName;
    }

    void setAttended(boolean attended)
    {
        wasAttended = attended;
    }

    void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    boolean isAttended()
    {
        return wasAttended;
    }

}
