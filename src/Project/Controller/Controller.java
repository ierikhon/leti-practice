package Project.Controller;

import Project.Model.GraphModel;
import Project.View.View;

public class Controller
{
    private final GraphModel model;
    private final View view;

    public GraphModel getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public Controller(GraphModel model, View view)
    {
        this.model = model;
        this.view = view;
    }



    public void viewUpdated()
    {
        view.draw(model);
    }
}
