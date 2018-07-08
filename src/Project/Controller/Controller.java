package Project.Controller;

import Project.Model.GraphModel;
import Project.View.View;


public class Controller
{
    private final View view;
    private final GraphModel model;
    private boolean wasUpd = false;

    public View getView() {
        return view;
    }

    public Controller(View view, GraphModel model)
    {
        this.view = view;
        this.model = model;
    }

    public void updated()
    {
        view.updateGraph(model);
        wasUpd = true;
    }

    public boolean wasUpdated()
    {
        if (!wasUpd)
            return false;
        else
        {
            wasUpd = false;
            return true;
        }
    }

    public String matrixUpdated()
    {
        return model.writeMatrix();
    }

    public void start()
    {
        model.transite();
        updated();
    }

    public void stop()
    {
        model.backToOriginal(model.stepsNumber()-1);
        updated();
    }

    public void forward()
    {
        model.stepFwd(model.stepsNumber());
        updated();
    }

    public void back()
    {
        model.stepBack(model.stepsNumber());
        updated();
    }
}
