package cs3500.animator.model;

/**
 * Represents a TimeInterval in the animation
 * with the interval bounds and the tempo for the interval.
 */
public class TimeInterval implements ITimeInterval {

  private int startTick;
  private int endTick;
  private int tempo;

  /**
   * Constructs a TimeInterval object.
   * @param startTick the start tick of the interval
   * @param endTick the end tick of the interval
   * @param tempo the tempo of this interval
   */
  public TimeInterval(int startTick, int endTick, int tempo) {

    if (startTick > endTick) {
      throw new IllegalArgumentException("End tick before start tick");
    }

    this.startTick = startTick;
    this.endTick = endTick;
    this.tempo = tempo;
  }

  @Override
  public int getStartTick() {
    return startTick;
  }

  @Override
  public int getEndTick() {
    return endTick;
  }

  @Override
  public int getTempo() {
    return tempo;
  }
}
