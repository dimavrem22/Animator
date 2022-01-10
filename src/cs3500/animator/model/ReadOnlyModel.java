package cs3500.animator.model;

import java.util.List;

/**
 * Read only version of Animation model.
 * Allows for obtaining information about the model but not modifying the model.
 */
public class ReadOnlyModel implements IReadOnlyModel {

  private IAnimationModel model;

  /**
   * Constructs a ReadOnlyModel.
   *
   * @param model an IAnimationModel to use for delegation
   */
  public ReadOnlyModel(IAnimationModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Null model");
    }
    this.model = model;
  }



  @Override
  public boolean hasShape(String nameOfShape) throws IllegalArgumentException {

    return model.hasShape(nameOfShape);
  }

  @Override
  public IShape getShape(String nameOfShape) throws IllegalArgumentException {
    return model.getShape(nameOfShape);
  }


  @Override
  public List<IMotion> getMotions(String nameOfShape) {
    return model.getMotions(nameOfShape);
  }

  @Override
  public IShape shapeAtTick(String name, int tick) {
    return model.shapeAtTick(name, tick);
  }

  @Override
  public List<String> getAllShapeNames() {
    return model.getAllShapeNames();
  }

  @Override
  public List<IShape> getAllShapes() {
    return model.getAllShapes();
  }

  @Override
  public int calculateLastTick() {
    return model.calculateLastTick();
  }

  @Override
  public int getTopmostY() {
    return model.getTopmostY();
  }

  @Override
  public int getLeftmostX() {
    return model.getLeftmostX();
  }

  @Override
  public int getWidthBound() {
    return model.getWidthBound();
  }

  @Override
  public int getHeightBound() {
    return model.getHeightBound();
  }

  @Override
  public int getTempoAtTick(int tick) {
    return model.getTempoAtTick(tick);
  }
}
