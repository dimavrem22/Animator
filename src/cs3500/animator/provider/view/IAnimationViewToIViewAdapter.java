package cs3500.animator.provider.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.provider.controller.FeaturesImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class IAnimationViewToIViewAdapter implements IView {

  private IVisualAnimationView delegate;

  public IAnimationViewToIViewAdapter(IVisualAnimationView delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }


  @Override
  public void render() throws IOException {

    delegate.render();

  }

  @Override
  public void play() {

    delegate.resumeAnimation();

  }

  @Override
  public void restart() {
    System.out.println("Restart from adapter to iview");
    delegate.restartAnimation();

  }

  @Override
  public void exit() {

    System.exit(0);

  }

  @Override
  public void increaseTempo(int amount) {

    delegate.fastForward(amount);

  }

  @Override
  public void decreaseTempo(int amount) {
    delegate.fastBackward(amount);

  }

  @Override
  public void stop() {

    delegate.pauseAnimation();


  }

  @Override
  public void setButtonListeners(ActionListener listener) {
    delegate.addFeatures(new FeaturesImpl(this));
  }

  @Override
  public void toggleLooping() {

    delegate.loop();

  }

  @Override
  public void advance(ActionEvent e) {

    delegate.resumeAnimation();

  }

  @Override
  public void toggleOutline() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void toggleDiscrete() {
    throw new UnsupportedOperationException();
  }
}
