<h5>Interface: IColor</h5>
- provides common functionality which all IColor objects share
- used to provide color to shapes 
- Class: Color 
    - provides an implementation of IColor
    
<h5>Interface: IPosition</h5>
- provides common functionality which all IPosition objects share
- used to provide positions to shapes 
- Class: Position 
    - provides an implementation of IPosition
    - represents Cartesian coordinates with integer precision 
    
<h5>Interface: IShape</h5>
- provides common functionality which all IShape objects share
- provides shapes for the animation to work on 
- used to provide a visual representation within an animation
- Abstract Class: AShape 
    - provides common implementation for shapes
    - allows general functionality of shapes such as setting size, color, and position 
    - Class: Rectangle
       - represents a rectangle shape 
    - Class: Ellipse 
       - represents an ellipse shape 
       
<h5>Interface: IMotion</h5> 
- provides common functionality which all IMotion objects share
- used to act on Shapes by moving them, resizing them, and changing their color 
- Class: Motion 
    - provides an implementation of IMotion 
    - used to transform shapes during particular time intervals throughout an animation 
      
<h5>Interface: IAnimationModel</h5>
- provides common functionality which all IAnimationModel objects share
- used to invoke basic functionality of an animation 
- Class: AnimationModel
    - provides an implementation for the IAnimationModel interface 
    - used to perform basic functionality of an animation: 
    - adding and removing shapes and their respective motions 
    - getting the states of shapes at particular times 
    - the list of shapes is stores as a LinkedHashMap to preserve order
    - the list of motions for each shape is stored as a LinkedHashMap
    - adding new motions to a list of shape's motions automatically puts it in the correct order 
      by start tick. 
    - the model only returns copies to objects rather than references, which does not allow for 
       mutability. 
            
                  
<h5>Interface: IView</h5> 
- provides common functionality which all IView objects share
- represents the visuals of the Animation
- Class: TextualView 
    - provides an implementation of the IView interface 
    - allows the user to render the shapes and their motions as text output
      
- Class: SVGView
    - provides an implementation of the IView interface
    - allows the user to render the shapes and their motions as SVG output
      
- Class: VisualViewOld 
    - provides an implementation of the IView interface
    - allows the user to render the shapes and their motions using a GUI
       in a non-interactive manner.
    
- Class: VisualView 
    - provides an implementation of the IView interface
    - allows the user to render the shapes and their motions using a GUI
    - allows the user to interact with the animation: 
      - play 
      - pause 
      - restart 
      - exit 
      - slow down/speed up 
      - enable/disable looping: this should be enabled 
      before the animation ends to see the looping in action.
      Otherwise, you can just restart the animation
      for it to loop back.

- Class: MockView
     - allows for input testing and checking
       whether proper method was called from controller.
        
               
 
<h5>Interface: AnimationBuilder</h5>

- Class Builder
    - provides a means of constructing the model
    with the right shapes and motions after file parsing
    
    
<h5>Interface: IReadOnlyModel</h5>

- allow the user to see and use the contents of an animationModel without permitting mutation 
    - Class ReadOnlyModel: 
       passed into all of the view implementations so that the view can not be used to 
       mutate the model 

     
<h5>Interface: IController</h5>
- represents common functionality for controllers
- Class: Controller 
     - provides an implementation of the IController interface
     - taken in a view(which already has a model)
     - acts as an actionListener for the Interactive visual view
     - takes in action event and tells the visual view what the effect should be (stop, play, restart, etc.)
        
<h5>Interface: ISort </h5>
- represents common functionality of sorting
    algos.
        
<h5>Abstract Class: AbstractSort </h5>

- provides some common implementation for translating sorting algorithms to files containing animation features
- allows for the visualization of sorting algorithms in which elements are swapped 
- Class: BubbleSort 
     - extends AbstractSort 
     - sorts an array of integers using the bubble sort technique 
     - creates a file "bubble.txt" containing the features of the representative animation
- Class: Selection 
     - extends AbstractSort 
     - sorts an array of integers using the selection sort technique 
     - creates a file "selection.txt" containing the features of the representative animation



<h5>Additions for hw6</h5>

- Created SVGView and VisualView
        
- Created ViewCreator class that constructs the proper view given the ViewType needed
              
- Created Excellence class which is the entry point of
the animation

- Created Builder class which constructs the model
with the right shapes and animations

- Created a FirstTickComparator which allows sorting
based on the smallest start tick value. 


<h5>Changes from previous assignment for hw7</h5>

- Made our old visual view button-free as per assignment specs
and made a new visual view that extends upon that old visual
view and has interactivity.

- Our IView holds all functionalities for views
and it is up to the views themselves
if they support the operation or not.

- Action handling is now done by the controller instead
of the view.

- Controller handles writing to files.

- View creator method now takes an Appendable
for rendering for SVG and Textual views.

- View Creator now includes Interactive as a type.

- Excellence main method handles for interactive view.

- Created MockView and ControllerTest for controller
  testing. 
  
- Removed "resources" from file path in Excellence.     
         
- Excellence main method uses controllers execute
    method                  
          







    
    
    
       
       

               
         
         
     
     
     
     
     
         
         
         
         
         
         

     