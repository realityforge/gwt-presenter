package net.customware.gwt.presenter.client.place;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import net.customware.gwt.presenter.client.place.PlaceRequestEvent.Handler;

public class PlaceRequestEvent
  extends GwtEvent<Handler>
{
  public static interface Handler
    extends EventHandler
  {
    /**
     * Called when something has requested a new place. Should be implemented by
     * instances which can show the place.
     *
     * @param event The event.
     */
    void onPlaceRequest( PlaceRequestEvent event );
  }

  private static final Type<Handler> TYPE = new Type<>();

  public static Type<Handler> getType()
  {
    return TYPE;
  }

  private final PlaceRequest request;

  private final boolean fromHistory;

  public PlaceRequestEvent( final PlaceRequest request )
  {
    this( request, false );
  }

  PlaceRequestEvent( final PlaceRequest request, final boolean fromHistory )
  {
    this.request = request;
    this.fromHistory = fromHistory;
  }

  @Override
  protected void dispatch( final Handler handler )
  {
    handler.onPlaceRequest( this );
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return PlaceRequestEvent.getType();
  }

  public PlaceRequest getRequest()
  {
    return request;
  }

  boolean isFromHistory()
  {
    return fromHistory;
  }
}
