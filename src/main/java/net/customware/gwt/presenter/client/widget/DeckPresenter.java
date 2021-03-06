package net.customware.gwt.presenter.client.widget;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * A simple implementation of {@link WidgetContainerPresenter} that uses a
 * {@link DeckPanel} to contain the provided presenters. Only one will be
 * visible at a time.
 *
 * @author David Peterson
 */
public abstract class DeckPresenter
  extends WidgetContainerPresenter<DeckDisplay>
{
  public DeckPresenter( final DeckDisplay display, final EventBus eventBus, final WidgetPresenter<?>... presenters )
  {
    super( display, eventBus, presenters );
  }
}
