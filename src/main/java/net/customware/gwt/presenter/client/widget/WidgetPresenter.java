package net.customware.gwt.presenter.client.widget;

import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import net.customware.gwt.presenter.client.BasicPresenter;

/**
 * Abstract super-class for {@link BasicPresenter}s that work with GWT
 * {@link Widget}s via {@link WidgetDisplay}s.
 *
 * @param <D> The {@link WidgetDisplay} type.
 * @author David Peterson
 */
public abstract class WidgetPresenter<D extends WidgetDisplay>
  extends BasicPresenter<D>
{
  public WidgetPresenter( final D display, final EventBus eventBus )
  {
    super( display, eventBus );
  }
}
