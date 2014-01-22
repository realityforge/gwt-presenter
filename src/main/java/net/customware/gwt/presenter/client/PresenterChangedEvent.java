package net.customware.gwt.presenter.client;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import net.customware.gwt.presenter.client.PresenterChangedEvent.Handler;

public class PresenterChangedEvent
  extends GwtEvent<Handler>
{
  public static interface Handler
    extends EventHandler
  {
    void onPresenterChanged( PresenterChangedEvent event );
  }

  private static final Type<Handler> TYPE = new Type<>();

  public static Type<Handler> getType()
  {
    return TYPE;
  }

  private final Presenter presenter;

  public PresenterChangedEvent( final Presenter presenter )
  {
    this.presenter = presenter;
  }

  public Presenter getPresenter()
  {
    return presenter;
  }

  @Override
  protected void dispatch( final Handler handler )
  {
    handler.onPresenterChanged( this );
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

}
