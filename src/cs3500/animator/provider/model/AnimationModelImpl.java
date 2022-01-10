package cs3500.animator.provider.model;

import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.Rectangle;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class AnimationModelImpl implements AnimationModel {

  private IAnimationModel delegate;

  public AnimationModelImpl(IAnimationModel delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }

  @Override
  public Map<String, IShapeAndMotion> getDrawings() {

    Map<String, IShapeAndMotion> m = new LinkedHashMap<String, IShapeAndMotion>();
    List<String> names = delegate.getAllShapeNames();
    System.out.println(names);
    for(int i = 0; i < names.size(); i++) {
      IShapeAndMotion sm = new ShapeAndMotion(this.delegate,names.get(i));
      m.put(names.get(i),sm);
    }
    //System.out.println(m);
    return m;


  }

  @Override
  public void startAnimation() {

    return;

  }

  @Override
  public void createShape(String obj, String name) throws IllegalArgumentException {
    IShape shape = null;
    if (obj == "rect") {
      shape = new Rectangle(1, 1, new Color(0,0,0),
          new Position(0,0),1.0);
    }
    else{
      shape = new Ellipse(1, 1, new Color(0,0,0),
          new Position(0,0),1.0);
    }

    delegate.addShape(name, shape);
  }

  @Override
  public void addOperation(String name, int start, int end) throws IllegalArgumentException {
    return;

  }

  @Override
  public void addOperation(String name, int start, int end, int x, int y, int w, int h, int r,
      int g, int b) throws IllegalArgumentException {

    IMotion motion = new Motion(name,delegate.getShape(name),
        new Position(x,y),start,end,w,h,new Color(r,g,b));

    delegate.addMotion(name,
        new Position(x,y),start,end,w,h,new Color(r,g,b));



  }

  @Override
  public void addOperation(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    return;

  }

  @Override
  public boolean isAnimationOver() throws IllegalStateException {
    return false;
  }

  @Override
  public Map<String, IShapeAndMotion> getInstructions() {
    Map<String,IShapeAndMotion> temp = this.getDrawings();
    return temp;

  }

  @Override
  public IShapeAndMotion getImageAt(String name) {
    return this.getDrawings().get(name);
  }

  @Override
  public void resetAnimation() {

    return;

  }

  @Override
  public IShape2D getFrame(String name, int time)
      throws IllegalStateException {
    return null;
  }

  @Override
  public void retrieve() throws IllegalStateException {
    return;

  }

  @Override
  public boolean animationStarted() {
    return true;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {

    return;

  }

  @Override
  public int getXCanvas() {
    return delegate.getLeftmostX();
  }

  @Override
  public int getYCanvas() {
    return delegate.getTopmostY();
  }

  @Override
  public int getWidthCanvas() {
    return delegate.getWidthBound();
  }

  @Override
  public int getHeightCanvas() {
    return delegate.getHeightBound();
  }

  @Override
  public int getEndTime() {
    return delegate.calculateLastTick();
  }

  @Override
  public void updateShape(String name, Position2D pos, Size2D size, Rgb color)
      throws IllegalArgumentException, IllegalStateException {

    IShape updated = this.delegate.getShape(name).create(size.getWidth(), size.getHeight(),
        new Color(color.getR(),color.getG(),color.getB()),
        new Position((int) pos.getX(),(int) pos.getY()), 1.0);

    delegate.setShape(name,updated);


  }
}
