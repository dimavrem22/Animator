package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Represents the interactive GUI view for the animation.
 * The model's information is used to display the state
 * of the animation in a Visual format. This particular implementation of the view
 * is dynamic (moving shapes can be visualised) and interactive.
 * The users are able to stop and resume the
 * animation, restart the animation, speed it
 * up or slow it down, and enable/disable looping.
 */
public class VisualView extends VisualViewOld {

  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton fasterButton;
  private JButton slowerButton;
  private JButton exitButton;
  private JButton loopToggleButton;
  private boolean loopAgain;
  private boolean outlineMode;
  private JButton outlineToggle;
  private boolean discreteMode;
  private JButton discreteToggle;

  private List<Integer> discreteTicks;




  /**
   * Constructs a interactive Visual view with tempo of 1.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if given model is null
   */
  public VisualView(IReadOnlyModel model) {
    this(model, 1);
  }


  /**
   * Constructs a interactive Visual view with given tempo.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @param tempo the ticks per second of the animation
   * @throws IllegalArgumentException if given model is null
   */
  public VisualView(IReadOnlyModel model, int tempo) {
    super(model,tempo,false);

    this.loopAgain = false;
    this.outlineMode = false;
    this.discreteMode = false;
    this.discreteTicks = getListOfTicks();

    // Init widgets
    this.playButton = new JButton("Play");
    this.pauseButton = new JButton("Pause");
    this.restartButton = new JButton("Restart");
    this.fasterButton = new JButton("Go Faster");
    this.slowerButton = new JButton("Go Slower");
    this.exitButton = new JButton("Exit");
    this.loopToggleButton = new JButton("Looping Off");
    this.outlineToggle = new JButton("Fill Mode");
    this.discreteToggle = new JButton("Continous");

    playButton.setActionCommand("playButton");
    pauseButton.setActionCommand("pauseButton");
    restartButton.setActionCommand("restartButton");
    fasterButton.setActionCommand("fasterButton");
    slowerButton.setActionCommand("slowerButton");
    exitButton.setActionCommand("exitButton");
    loopToggleButton.setActionCommand("toggleButton");
    outlineToggle.setActionCommand("outlineButton");
    discreteToggle.setActionCommand("discreteButton");


    // Add Buttons to JPanel container
    JPanel buttons = new JPanel();
    buttons.add(playButton);
    buttons.add(pauseButton);
    buttons.add(restartButton);
    buttons.add(fasterButton);
    buttons.add(slowerButton);
    buttons.add(loopToggleButton);
    buttons.add(exitButton);
    buttons.add(outlineToggle);
    buttons.add(discreteToggle);


    // Add buttons
    add(buttons, BorderLayout.NORTH);
    //add(scrollPane, BorderLayout.EAST);

    pack();
    setVisible(true);

  }


  @Override
  public void advance(ActionEvent e) {

    if (!discreteMode) {
      super.advance(e);
    }
    // Discrete Mode
    else {
      int discreteTick = getDiscreteTick();
      if (discreteTick != -1) {
        this.tick = discreteTick;
      }
      drawPanel.setTick(tick);
      // set new tempo
      repaint();

    }


    if (this.tick >= model.calculateLastTick()) {
      if (this.loopAgain) {
        restart();
      }
    }

    drawPanel.setOutlined(this.outlineMode);
  }

  /**
   * Gets the discrete ticks needed to be played.
   * @return the list of ticks to render in discrete mode
   */
  private List<Integer> getListOfTicks() {

    List<Integer> result = new ArrayList<Integer>();


    for (String name : model.getAllShapeNames()) {
      for (IMotion m : model.getMotions(name)) {

        if (!result.contains(m.getStartTick())) {
          result.add(m.getStartTick());
        }

        if (!result.contains(m.getLastTick())) {
          result.add(m.getLastTick());
        }
      }
    }

    Collections.sort(result);
    return result;

  }

  /**
   * Gets the current discrete tick to display.
   * @return the discrete tick to render
   */
  private int getDiscreteTick() {

    for (int i = 0; i < discreteTicks.size(); i++) {

      if (this.tick < discreteTicks.get(i)) {
        return discreteTicks.get(i);
      }

    }

    return -1;

  }



  @Override
  public void stop() {
    timer.stop();
  }

  @Override
  public void setButtonListeners(ActionListener listener) {

    if (listener == null) {
      throw new IllegalArgumentException("Args should be non-null");
    }

    playButton.addActionListener(listener);
    pauseButton.addActionListener(listener);
    restartButton.addActionListener(listener);
    fasterButton.addActionListener(listener);
    slowerButton.addActionListener(listener);
    exitButton.addActionListener(listener);
    loopToggleButton.addActionListener(listener);
    outlineToggle.addActionListener(listener);
    discreteToggle.addActionListener(listener);

  }


  @Override
  public void play() {
    timer.start();
  }

  @Override
  public void restart() {
    this.tick = 0;
    this.play();
  }

  @Override
  public void exit() {
    System.exit(0);
  }

  @Override
  public void increaseTempo(int amount) {
    this.tempo += amount;
    timer.setDelay(1000 / this.tempo);
    this.originalTempo = this.tempo;
  }

  @Override
  public void decreaseTempo(int amount) {

    if (tempo - amount <= 0) {
      return;
    }
    this.tempo -= amount;
    timer.setDelay(1000 / this.tempo);
    this.originalTempo = this.tempo;
  }

  @Override
  public void toggleLooping() {

    if (this.loopAgain) {
      this.loopToggleButton.setText("Looping Off");
    }
    else {
      this.loopToggleButton.setText("Looping On");

    }

    this.loopAgain = !this.loopAgain;

  }

  @Override
  public void toggleOutline() {

    if (this.outlineMode) {
      this.outlineToggle.setText("Fill Mode");
    }
    else {
      this.outlineToggle.setText("Outline mode");
    }

    this.outlineMode = !this.outlineMode;
  }

  @Override
  public void toggleDiscrete() {
    if (this.discreteMode) {
      this.discreteToggle.setText("Continous");
    }
    else {
      this.discreteToggle.setText("Discrete");
    }

    this.discreteMode = !this.discreteMode;
  }

}
