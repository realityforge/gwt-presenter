package net.customware.gwt.presenter.client.place;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import net.customware.gwt.presenter.client.place.PlaceRevealedEvent.Handler;

public class PlaceRevealedEvent
  extends GwtEvent<Handler>
{
  public static interface Handler
    extends EventHandler
  {
    /**
     * Called when a {@link net.customware.gwt.presenter.client.place.Place} has been revealed to the user.
     *
     * @param event The even.
     */
    void onPlaceRevealed( PlaceRevealedEvent event );
  }

  private static GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<>();

  public static GwtEvent.Type<Handler> getType()
  {
    return TYPE;
  }

  private final Place place;

  public PlaceRevealedEvent( Place place )
  {
    this.place = place;
  }

  /**
   * Returns the place that was revealed.
   *
   * @return the place.
   */
  public Place getPlace()
  {
    return place;
  }

  @Override
  protected void dispatch( Handler handler )
  {
    handler.onPlaceRevealed( this );
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType()
  {
    return TYPE;
  }
}
