<h1> Animator </h1>

<h2> About Project: </h2> 
Animator utilizes the Model-View-Controller design pattern to render animations
in a variety of formats: 
<p> 
* <b>SVG</b> (viewed through a browser)<br/>
* <b>Visual</b> (viewed through a java.awt window)<br/>
* <b>Interactive</b> (viewed and controlled through a java.awt window)<br/>
* <b>Text</b> (lists all shapes and their respective motions)
</p>

<h2> How To Run Animator: </h2> 
Animator can be run through the <i><b>Main()</b></i> method of the <i><b>Excellence</b></i> class in conjunction 
with the following command line arguments:
<p> 
<b>"-in"</b> is followed by the name of the animation file in the <i>resources</i> directory
which Animator will render<br/>

<b>"-out"</b> is followed by the name of the file to which the Text or SVG contents will be written
default is set to <i>System.out</i>

<b>"-view"</b> is followed by the type of view used to render the animation:
"svg", "text", "visual", or "interactive

<b>"-speed"</b> is followed by the integer value corresponding to how fast the animation will run
<h4> Examples: </h4>
><b> -in   toh-8.txt -view visual -speed 10"</b>

- *toh-8.txt* file from the *resources* directory will be used for to build the model
- the text containing information about the shapes and motions will be printed in *System.out*
- the animation will appear in a java.awt window without capacity for user control
- the animation will run at a speed of 10

><b>  -in buildings.txt -out info.txt -view interactive -speed 30</b>

- *buildings.txt* file from the *resources* directory will be used for to build the model
- the text containing information about the shapes and motions will be printed in *info.txt* file 
in the *resources* directory
- the animation will appear in a java.awt window with the capacity for user control
- the animation will run at a speed of 30

><b>  -in buildings.txt -out buildings.svg -view svg -speed 5</b>

- *buildings.txt* file from the *resources* directory will be used for to build the model
- the svg text containing information about the shapes and motions will be printed in *buildings.svg* file
  in the *resources* directory
    - animation can be viewed by opening the generated svg file in a browser
- the animation will run at a speed of 5

</p>

<h2> Project Structure: </h2> 

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
       passed into all the view implementations so that the view can not be used to 
       mutate the model 

     
<h5>Interface: IController</h5>
- represents common functionality for controllers
- Class: Controller 
     - provides an implementation of the IController interface
     - taken in a view(which already has a model)
     - acts as an actionListener for the Interactive visual view
     - takes in action event and tells the visual view what the effect should be (stop, play, restart, etc.)
     