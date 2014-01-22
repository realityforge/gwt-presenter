package net.customware.gwt.presenter.client.place;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import net.customware.gwt.presenter.client.place.PlaceChangedEvent.Handler;

/**
 * This event is triggered when any {@link Place} has changed state. It may or
 * may not be the current 'revealed' place.
 *
 * @author David Peterson
 */
public class PlaceChangedEvent
  extends GwtEvent<Handler>
{
  public static interface Handler
    extends EventHandler
  {
    /**
     * Called after the current place has already changed. Allows handlers to
     * update any internal tracking, etc.
     *
     * @param event The event.
     */
    void onPlaceChanged( PlaceChangedEvent event );
  }

  private static final Type<Handler> TYPE = new Type<>();

  public static Type<Handler> getType()
  {
    return TYPE;
  }

  private final Place place;

  public PlaceChangedEvent( final Place place )
  {
    this.place = place;
  }

  @Override
  protected void dispatch( final Handler handler )
  {
    handler.onPlaceChanged( this );
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  public Place getPlace()
  {
    return place;
  }
}
