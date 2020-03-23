# Simple-Example-MVP

<b>Model View Presenter (MVP)</b>
The MVP pattern is similar to the MVC pattern. It is derived from MVC pattern, wherein the controller is replaced by the presenter. This pattern divides an application into three major aspects: Model, View, and Presenter.
Model
The Model represents a set of classes that describes the business logic and data. It also defines business rules for data means how the data can be changed and manipulated.
View
View is a component which is directly interacts with user like XML, Activity, fragments. It does not contain any logic implemented.
Presenter
The Presenter receives the input from users via View, then process the user’s data with the help of Model and passing the results back to the View. Presenter communicates with view through interface. Interface is defined in presenter class, to which it pass the required data. Activity/fragment or any other view component implement this interface and renders the data in a way they want.
In the MVP design pattern, the presenter manipulates the model and also updates the view. In MVP View and Presenter are completely decoupled from each other’s and communicate to each other’s by an interface. Because if decoupling mocking of the view is easier and unit testing of applications that leverage the MVP design pattern over the MVC design pattern are much easier.
