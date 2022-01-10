package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;


/**
 * Represents the GUI view for the animation. The model's information is used to
 * display the state of the animation in a Visual format.
 * This implementation of the view does not allow for interaction
 * with the user.
 */
public class VisualViewOld extends JFrame implements IView {

  protected final IReadOnlyModel model;
  protected final DrawingPanel drawPanel;
  protected int tempo; // ticks per second
  protected int originalTempo;
  protected Timer timer;
  protected int tick;
  protected JScrollPane scrollPane;

  /**
   * Constructs a Visual view.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if given model is null
   */
  public VisualViewOld(IReadOnlyModel model) {
    this(model, 1,true);
  }


  /**
   * Constructs a Visual view.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @param tempo the ticks per second of the animation
   * @throws IllegalArgumentException if given model is null
   */
  public VisualViewOld(IReadOnlyModel model, int tempo) {
    this(model,tempo,true);
  }

  /**
   * Constructs a Visual view.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @param tempo the ticks per second of the animation
   * @param visible true if animation should be visible, false otherwise
   *
   * @throws IllegalArgumentException if given model is null
   */
  public VisualViewOld(IReadOnlyModel model, int tempo,boolean visible) {
    super("Visual View");

    if (model == null) {
      throw new IllegalArgumentException("Argument should not be null.");
    }


    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.model = model;
    this.tempo = tempo;
    this.originalTempo = tempo;
    this.tick = 0;

    // Set layout for canvas
    this.setLayout(new BorderLayout());

    //Init drawpanel
    drawPanel = new DrawingPanel(model, this.tick);
    drawPanel.setBackground(Color.white);
    drawPanel.setPreferredSize(new Dimension(model.getWidthBound() + model.getLeftmostX(),
        model.getHeightBound() + model.getTopmostY()));

    this.scrollPane = new JScrollPane(drawPanel);
    this.timer = new Timer(1000 / tempo, this::advance);

    add(scrollPane, BorderLayout.EAST);

    pack();

    setVisible(visible);



  }

  @Override
  public void render() {
    timer.start();
    drawPanel.setTick(tick);
  }

  @Override
  public void advance(ActionEvent e) {

    if (this.tick > model.calculateLastTick()) {
      timer.stop();
    } else {
      this.tick += 1;
      if (model.getTempoAtTick(tick) == -1) {
        tempo = originalTempo;
      }
      else {
        tempo = model.getTempoAtTick(tick);
      }

      timer.setDelay(1000/tempo);

      //System.out.println(tempo);

      drawPanel.setTick(tick);
      // set new tempo
      repaint();
    }
  }

  @Override
  public void toggleOutline() {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void toggleDiscrete() {
    throw new UnsupportedOperationException("Can't perform operation");
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("Can't perform this operation.");
  }

  @Override
  public void restart() {
    throw new UnsupportedOperationException("Can't perform this operation.");

  }

  @Override
  public void exit() {
    throw new UnsupportedOperationException("Can't perform this operation.");

  }

  @Override
  public void increaseTempo(int amount) {
    throw new UnsupportedOperationException("Can't perform this operation.");

  }

  @Override
  public void decreaseTempo(int amount) {
    throw new UnsupportedOperationException("Can't perform this operation.");

  }

  @Override
  public void stop() {
    throw new UnsupportedOperationException("Can't perform this operation.");

  }

  @Override
  public void setButtonListeners(ActionListener listener) {
    throw new UnsupportedOperationException("Can't perform this operation.");
  }

  @Override
  public void toggleLooping() {
    throw new UnsupportedOperationException("Can't perform this operation.");
  }
}
