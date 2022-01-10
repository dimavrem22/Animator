package cs3500.animator.model;

import cs3500.animator.util.AnimationReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import cs3500.animator.util.AnimationBuilder;

/**
 * Class representing an implementation of {@link IAnimationModel}. The class allows its users to
 * manage shapes and their associated motions that make up a given animation. Shapes and motions can
 * be added and removed. The states of shapes at various points in the animation can be obtained.
 * <p>
 * INVARIANT: names of shapes in shapeMotions and shapes are found in an identical order (enforced
 * by the constructor and all methods within the class).
 * </p>
 * <p>
 * Note: The model allows for gaps between motions. Therefore, we do not need to store motions that
 * describe stationary movement.
 * </p>
 */
public class AnimationModel implements IAnimationModel {

  private final Map<String, List<IMotion>> shapeMotions;
  private final Map<String, IShape> shapes;
  private final Map<String, IShape> inits;
  private final List<TimeInterval> intervals;
  private int widthBound;
  private int heightBound;
  private int leftmostX;
  private int topmostY;


  /**
   * Constructs an animation model with default parameter of 1000, 1000 (bounds)
   * and 0, 0 (position).
   */
  public AnimationModel() {
    this(1000, 1000, 0, 0);
  }


  /**
   * Constructs an animation model.
   * @param widthBound  width of the bound
   * @param heightBound  height of the bound
   * @param leftMostX    the x-coordinate of the left most position
   * @param leftMostY    the y-coordinate of the left most position
   */
  public AnimationModel(int widthBound, int heightBound,
      int leftMostX, int leftMostY) {
    this.intervals = new ArrayList<TimeInterval>();
    // LinkedHashMap preserves insertion order
    this.shapeMotions = new LinkedHashMap<String, List<IMotion>>();
    this.shapes = new LinkedHashMap<String, IShape>();
    this.widthBound = widthBound;
    this.heightBound = heightBound;
    this.leftmostX = leftMostX;
    this.topmostY = leftMostY;
    this.inits = new LinkedHashMap<String, IShape>();
  }


  @Override
  public void addShape(String name, IShape shape) throws IllegalArgumentException {

    if (name == null || shape == null) {
      throw new IllegalArgumentException("Arguments should be non-null");
    }

    if (hasShape(name)) {
      throw new IllegalArgumentException("Shape name already in use");
    }

    this.shapeMotions.put(name, new ArrayList<IMotion>());
    this.shapes.put(name, shape);
    IShape copy = shape.clone();
    this.inits.put(name, copy);

  }

  @Override
  public void removeShape(String nameOfShape) throws IllegalArgumentException {

    if (nameOfShape == null) {
      throw new IllegalArgumentException("Should be non-null");
    }

    if (!hasShape(nameOfShape)) {
      throw new IllegalArgumentException("Shape does not exist.");
    }

    shapes.remove(nameOfShape);
    inits.remove(nameOfShape);
    shapeMotions.remove(nameOfShape);

  }

  @Override
  public boolean hasShape(String nameOfShape) throws IllegalArgumentException {
    if (nameOfShape == null) {
      throw new IllegalArgumentException("Should be non-null");
    }

    return shapes.containsKey(nameOfShape);

  }

  @Override
  public IShape getShape(String nameOfShape) throws IllegalArgumentException {
    if (nameOfShape == null) {
      throw new IllegalArgumentException("Should be non-null");
    }

    if (!hasShape(nameOfShape)) {
      throw new IllegalArgumentException("Shape does not exist.");
    }

    return shapes.get(nameOfShape);

  }

  @Override
  public void addMotion(String nameOfShape, IPosition finalPosition, int firstTick,
      int lastTick, int finalWidth, int finalHeight, IColor finalColor)
      throws IllegalArgumentException {

    if (nameOfShape == null || finalPosition == null || finalColor == null) {
      throw new IllegalArgumentException("Should be non-null");
    }

    if (!hasShape(nameOfShape)) {
      throw new IllegalArgumentException("Shape does not exist.");
    }

    if (firstTick < 0 || lastTick < 0) {
      throw new IllegalArgumentException("Ticks should be non-negative.");
    }

    if (lastTick < firstTick) {
      throw new IllegalArgumentException("Final tick should be after initial tick.");
    }

    IMotion motion = null;
    if (shapeMotions.get(nameOfShape).size() == 0) {
       motion = new Motion(nameOfShape, shapes.get(nameOfShape), finalPosition, firstTick,
          lastTick, finalWidth, finalHeight, finalColor);
    }
    else{
       motion = new Motion(nameOfShape, shapeAtTick(nameOfShape,firstTick), finalPosition, firstTick,
          lastTick, finalWidth, finalHeight, finalColor);
    }


    List<IMotion> currMotions = this.shapeMotions.get(nameOfShape);
    // Check for any overlaps
    for (int i = 0; i < currMotions.size(); i++) {
      if (currMotions.get(i).overlaps(motion)) {
        throw new IllegalArgumentException("Motions should not overlap.");
      }
    }

    if (motion.getStartTick() == motion.getLastTick()) {
      return;
    }

    // Use boolean flag to check if the motion was added to the list of motions
    // when looking for greater start tick than its own start tick
    boolean haveAdded = false;
    for (int i = 0; i < currMotions.size(); i++) {
      if (motion.getStartTick() < currMotions.get(i).getStartTick()) {
        this.shapeMotions.get(nameOfShape).add(i, motion);
        haveAdded = true;
      }
    }

    if (!haveAdded) {
      this.shapeMotions.get(nameOfShape).add(motion);
    }
  }

  @Override
  public void setShape(String nameOfShape, IShape shape) {
    shapes.replace(nameOfShape,shape.clone());
  }

  @Override
  public List<IMotion> getMotions(String nameOfShape) {
    if (nameOfShape == null) {
      throw new IllegalArgumentException("Should be non-null");
    }

    if (!hasShape(nameOfShape)) {
      throw new IllegalArgumentException("Shape does not exist.");
    }

    List<IMotion> motions = new ArrayList<IMotion>();
    for (IMotion motion: this.shapeMotions.get(nameOfShape)) {
      motions.add(motion.clone());
    }

    return motions;



  }


  @Override
  public IShape shapeAtTick(String name, int tick) {
    if (name == null) {
      throw new IllegalArgumentException("Should be non-null");
    }
    if (!hasShape(name)) {
      throw new IllegalArgumentException("Shape does not exist.");
    }

    if (tick < 0) {
      throw new IllegalArgumentException("Tick should not be negative.");
    }

    List<IMotion> motions = shapeMotions.get(name);
    //IShape cloneOfOriginal = shapes.get(name).clone();
    IShape cloneOfOriginal = shapes.get(name).clone();

    if (motions.get(0).getStartTick() > tick) {
      return cloneOfOriginal.create(0, 0, new Color(0,0,0),
          new Position(0,0), 0.0);
    }

    for (int i = 0; i < motions.size(); i++) {
      IMotion currMotion = motions.get(i);


      if (isMotionCompleted(currMotion, tick)) {
        IMotion cloneMotion = new Motion(name, cloneOfOriginal, currMotion.getFinalPosition(),
            currMotion.getStartTick(),
            currMotion.getLastTick(), currMotion.getFinalWidth(), currMotion.getFinalHeight(),
            currMotion.getFinalColor());
        cloneMotion.goToFinalState();
      }

      if (isMotionInProgress(currMotion, tick)) {

        int tickDiff = currMotion.getLastTick() - currMotion.getStartTick();

        double xDiff = (currMotion.getFinalPosition().getX() - cloneOfOriginal.getCurrentPosition()
            .getX());
        double yDiff = (currMotion.getFinalPosition().getY() - cloneOfOriginal.getCurrentPosition()
            .getY());
        double widthDiff = currMotion.getFinalWidth() - cloneOfOriginal.getWidth();
        double heightDiff = currMotion.getFinalHeight() - cloneOfOriginal.getHeight();
        double redDiff = currMotion.getFinalColor().getRed() - cloneOfOriginal.getColor().getRed();
        double greenDiff =
            currMotion.getFinalColor().getGreen() - cloneOfOriginal.getColor().getGreen();
        double blueDiff =
            currMotion.getFinalColor().getBlue() - cloneOfOriginal.getColor().getBlue();

        int newX = (int) Math.round(((xDiff / tickDiff) * (tick - currMotion.getStartTick())) +
            cloneOfOriginal.getCurrentPosition().getX());
        int newY = (int) Math.round(((yDiff / tickDiff) * (tick - currMotion.getStartTick())) +
            cloneOfOriginal.getCurrentPosition().getY());
        int newWidth = (int) Math
            .round(((widthDiff / tickDiff) * (tick - currMotion.getStartTick())) +
                cloneOfOriginal.getWidth());
        int newHeight = (int) Math
            .round(((heightDiff / tickDiff) * (tick - currMotion.getStartTick())) +
                cloneOfOriginal.getHeight());
        int newRed = Math.max(0, Math.min(255, Math.round((int) ((redDiff / tickDiff) *
            (tick - currMotion.getStartTick())) + cloneOfOriginal.getColor().getRed())));
        int newGreen = Math.max(0, Math.min(255, (int) Math.round(((greenDiff / tickDiff) *
            (tick - currMotion.getStartTick())) + cloneOfOriginal.getColor().getGreen())));
        int newBlue = Math.max(0, Math.min(255, (int) Math.round(((blueDiff / tickDiff) *
            (tick - currMotion.getStartTick())) + cloneOfOriginal.getColor().getBlue())));
        IColor newColor = new Color(newRed, newGreen, newBlue);
        IPosition newPosition = new Position(newX, newY);
        double newOpacity = cloneOfOriginal.getOpacity();

        cloneOfOriginal = cloneOfOriginal.create(newWidth, newHeight, newColor, newPosition,
            newOpacity);
      }

    }

    return cloneOfOriginal;

  }


  /**
   * Given a motion, check if it is completed at the given tick.
   *
   * @param motion the cs3500.animator.model.IMotion in shapeMotions
   * @param tick   the current tick
   * @return true if the motion is completed at the given tick, false otherwise
   */
  private boolean isMotionCompleted(IMotion motion, int tick) {

    return tick >= motion.getLastTick();

  }

  /**
   * Determines whether a given cs3500.animator.model.IMotion is in progress at the given tick.
   *
   * @param motion a motion acting on some shape
   * @param tick   the tick to check at
   * @return true if the motion is in progress, false otherwise
   */
  private boolean isMotionInProgress(IMotion motion, int tick) {

    return tick > motion.getStartTick() && tick < motion.getLastTick();

  }

  @Override
  public List<String> getAllShapeNames() {

    List<String> names = new ArrayList<String>();

    for (String key : shapes.keySet()) {
      names.add(key);
    }

    return names;

  }

  @Override
  public List<IShape> getAllShapes() {
    List<String> names = getAllShapeNames();
    List<IShape> theShapes = new ArrayList<IShape>();
    for (String key : shapes.keySet()) {
      theShapes.add(shapes.get(key).clone());
    }

    return theShapes;

  }

  @Override
  public int calculateLastTick() {
    int currMax = 0;

    for (String key : shapeMotions.keySet()) {

      for (int i = 0; i < shapeMotions.get(key).size(); i++) {
        currMax = Math.max(currMax, shapeMotions.get(key).get(i).getLastTick());
      }

    }
    return currMax;
  }


  @Override
  public int getTopmostY() {
    return this.topmostY;
  }

  @Override
  public int getLeftmostX() {
    return this.leftmostX;
  }

  @Override
  public int getWidthBound() {
    return this.widthBound;
  }

  @Override
  public int getHeightBound() {
    return this.heightBound;
  }

  @Override
  public int getTempoAtTick(int tick) {

    for (TimeInterval t : intervals) {
      if (t.getStartTick() <= tick && t.getEndTick() >= tick) {
        return t.getTempo();
      }
    }

    return -1;


  }

  @Override
  public void addTimeInterval(int startTick, int endTick, int tempo) {

//    System.out.println("Start " + startTick);
//    System.out.println("End " + endTick);
//    System.out.println("Tewmpo " + tempo);
//    System.out.println(intervals.size());

    if (!checkIntervalOverlap(startTick, endTick)) {
      TimeInterval interval = new TimeInterval(startTick,endTick,tempo);
      intervals.add(interval);
      System.out.println("HEREEE");
    }
    else {
      throw new IllegalArgumentException("TimeIntervals overlap!");
    }
  }

  /**
   * Check if given interval overlaps with any TimeInterval
   * @param startTick start tick of interval
   * @param endTick end tick of interval
   * @return true if overlaps, false otherwise
   */
  private boolean checkIntervalOverlap(int startTick, int endTick) {


//    other.getStartTick() < this.lastTick &&
//        this.firstTick < other.getLastTick();

    for (TimeInterval t : intervals) {
      if (t.getStartTick() < endTick && startTick < t.getEndTick()) {
        return true;
      }

    }

    return false;

  }


  /**
   * The {@code Builder} class is used to construct an {@link IAnimationModel} based on input from
   * the {@link AnimationReader} with methods to modify its instance variables for model creation.
   * When the model's instance variables are modified to the user's liking, they can construct the
   * {@link IAnimationModel} by using the {@code build()} method.
   */
  public static final class Builder implements AnimationBuilder<IAnimationModel> {


    private final Map<String, List<IMotion>> shapeMotions;
    private final Map<String, IShape> shapes;
    private final List<TimeInterval> intervals;
    private int widthBound;
    private int heightBound;
    private int leftmostX;
    private int topmostY;


    /**
     * Constructs a Builder object.
     *
     * @param widthBound  the width bound for the animation
     * @param heightBound the height bound for the animation
     * @param leftmostX   the leftmost x for the animation
     * @param topmostY    the topmost y for the animation
     */
    public Builder(int widthBound, int heightBound, int leftmostX, int topmostY) {
      this.shapeMotions = new LinkedHashMap<String, List<IMotion>>();
      this.shapes = new LinkedHashMap<String, IShape>();
      this.intervals = new ArrayList<TimeInterval>();
      this.widthBound = widthBound;
      this.heightBound = heightBound;
      this.leftmostX = leftmostX;
      this.topmostY = topmostY;
    }


    @Override
    public IAnimationModel build() {
      IAnimationModel model = new AnimationModel(widthBound, heightBound, leftmostX, topmostY);
      for (Map.Entry<String, IShape> entry : shapes.entrySet()) {

        String name = entry.getKey();
        IShape shape = entry.getValue();
        model.addShape(name, shape);

        for (IMotion motion : shapeMotions.get(name)) {
          IPosition finalPosition = new Position(motion.getFinalPosition().getX(),
              motion.getFinalPosition().getY());
          IColor finalColor = new Color(motion.getFinalColor().getRGB());
          model.addMotion(name, finalPosition, motion.getStartTick(), motion.getLastTick(),
              motion.getFinalWidth(), motion.getFinalHeight(), finalColor);
        }


      }

      for (TimeInterval t: intervals) {
        System.out.println(intervals.size());
        try {
          model.addTimeInterval(t.getStartTick(),t.getEndTick(),t.getTempo());

        }
        catch (IllegalArgumentException ex) {
          System.out.println("Can't add this interval due to overlap!");
        }
      }
      return model;
    }

    @Override
    public AnimationBuilder<IAnimationModel> setBounds(int x, int y, int width, int height) {

      this.leftmostX = x;
      this.topmostY = y;
      this.widthBound = width;
      this.heightBound = height;
      return this;
    }

    @Override
    public AnimationBuilder<IAnimationModel> declareShape(String name, String type) {

      if (name == null || type == null) {
        throw new IllegalArgumentException("args should be non-null.");
      }

      switch (type) {
        case "rectangle":
          this.shapes.put(name, new Rectangle(0, 0, new Color(0, 0, 0),
              new Position(0, 0), 0));
          this.shapeMotions.put(name, new ArrayList<IMotion>());
          break;

        case "ellipse":
          this.shapes.put(name, new Ellipse(0, 0, new Color(0, 0, 0),
              new Position(0, 0), 0));
          this.shapeMotions.put(name, new ArrayList<IMotion>());

          break;

        case "plus":

          this.shapes.put(name, new Plus(0, 0, new Color(0, 0, 0),
              new Position(0, 0), 0));
          this.shapeMotions.put(name, new ArrayList<IMotion>());

          break;


        default:
          throw new IllegalArgumentException("Shape type not found.");
      }

      return this;
    }

    @Override
    public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {

      if (name == null) {
        throw new IllegalArgumentException("args should be non-null.");
      }

      IShape placeholder = this.shapes.get(name);

      if (shapeMotions.get(name).size() == 0 || shapeMotions.get(name).get(0).getStartTick() > t1) {
        String type = placeholder.toString();
        IShape editedToStartShape;
        switch (type) {
          case "rectangle":
            editedToStartShape = new Rectangle(w1, h1, new Color(r1, g1, b1), new Position(x1, y1),
                1);
            break;

          case "ellipse":
            editedToStartShape = new Ellipse(w1, h1, new Color(r1, g1, b1), new Position(x1, y1),
                1);
            break;


          case "plus":
            editedToStartShape = new Plus(w1, h1, new Color(r1, g1, b1), new Position(x1, y1),
                1);
            break;

          default:
            editedToStartShape = placeholder;
        }
        shapes.replace(name, editedToStartShape);
      }

      IMotion motion = new Motion(name, shapes.get(name), new Position(x2, y2), t1, t2, w2, h2,
          new Color(r2, g2, b2));
      shapeMotions.get(name).add(motion);
      Collections.sort(shapeMotions.get(name), new FirstTickComparator());

      return this;

    }

    @Override
    public AnimationBuilder<IAnimationModel> addInterval(int startTick, int endTick, int tempo) {

      intervals.add(new TimeInterval(startTick,endTick,tempo));
      return this;
    }


  }


}
