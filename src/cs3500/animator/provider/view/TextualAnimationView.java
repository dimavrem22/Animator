package cs3500.animator.provider.view;

import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.IReadAnimationModel;
import cs3500.animator.provider.model.IShapeAndMotion;
import cs3500.animator.provider.model.ViewModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Displays the shape model and motions in this animation in a String textual form. Has
 * the fields view model, out and speed in ticks per second. The viewModel is passed in so that
 * a direct reference to a model is not possible and therefore can't be mutated. The out
 * appendable is where we append the result to and speed is the ticks per second of the animation.
 */
public class TextualAnimationView implements ITextualAnimationView {

  private IReadAnimationModel viewModel;
  private Appendable out;
  private final int speed; // ticks per second


  /**
   * Displays the model in string form and also ensures that it takes in a nonnull model.
   * @param viewModel takes in the model to represent it in a string form
   * @param out appendable where we append the animation to
   * @param speed in ticks per second of the animation
   */
  public TextualAnimationView(IReadAnimationModel viewModel, Appendable out, int speed) {
    this.viewModel = Objects.requireNonNull(viewModel);
    this.out = Objects.requireNonNull(out);
    this.speed = speed;
  }

  @Override
  public void render() throws IOException {
    Objects.requireNonNull(this.out);
    this.out.append(this.toString());
    this.out.append("\n");
  }

  @Override
  public double convertTickToSeconds(int tick) {
    return ((double) tick) / this.speed;
  }

  /**
   * Provides a textual visualization of the shapes and motions contained in this animation.
   * @return a string with the shape created followed by its associated motions on each line
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    if (this.viewModel.animationStarted()) {
      if (!this.viewModel.isAnimationOver()) {

        // adding canvas information
        result.append(String.format("canvas %d %d %d %d", viewModel.getXCanvas(),
                viewModel.getYCanvas(),
                viewModel.getWidthCanvas(), viewModel.getHeightCanvas()));

        if (!this.viewModel.getInstructions().equals(new HashMap<>())) {
          result.append("\n");
        }

        Map<String, ?> drawings = this.viewModel.getInstructions();
        List<String> names = new ArrayList<>(drawings.keySet());
        for (String name : names) {
          IShapeAndMotion sm = viewModel.getImageAt(name);
          // shape creation line
          result.append(String.format("shape %s %s", name, sm.getShape().shapeName()));
          for (IMotion m : sm.getMotions()) {
            result.append("\n");
            result.append(String.format("motion %s %.2f %.0f %.0f %d %d %d %d %d   "
                            + "%.2f %.0f %.0f %d %d %d %d %d", m.getShapeName(),
                    this.convertTickToSeconds(m.getStartTime()),
                    m.getStartPos().getX(),
                    m.getStartPos().getY(), m.getStartSize().getWidth(),
                    m.getStartSize().getHeight(),
                    m.getStartColor().getR(), m.getStartColor().getG(), m.getStartColor().getB(),
                    this.convertTickToSeconds(m.getEndTime()),
                    m.getEndPos().getX(), m.getEndPos().getY(), m.getEndSize().getWidth(),
                    m.getEndSize().getHeight(),
                    m.getEndColor().getR(), m.getEndColor().getG(), m.getEndColor().getB()));
          }

          if (names.indexOf(name) != (names.size() - 1)) {
            result.append("\n");
          }
        }
      }
    }


    return result.toString();
  }
}

