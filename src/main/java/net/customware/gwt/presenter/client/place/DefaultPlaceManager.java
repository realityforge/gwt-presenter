package net.customware.gwt.presenter.client.place;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import net.customware.gwt.presenter.client.EventBus;

import java.util.HashMap;
import java.util.Map;

public abstract class DefaultPlaceManager implements PlaceManager {
    private class PlaceEventHandler implements ValueChangeHandler<String>, PlaceRevealedHandler,
        PlaceChangedHandler {

        public void onPlaceRevealed( PlaceRevealedEvent event ) {
            updateHistory( event.getPlace() );
        }

        public void onPlaceChanged( PlaceChangedEvent event ) {
            Place place = event.getPlace();
            try {
                if ( place.matchesRequest( tokenFormatter.toPlaceRequest( History.getToken() ) ) ) {
                    // Only update if the change comes from a place that matches
                    // the current location.
                    updateHistory( event.getPlace() );
                }
            } catch ( TokenFormatException e ) {
                // Do nothing...
            }
        }

        /**
         * Handles change events from {@link History}.
         */
        public void onValueChange( ValueChangeEvent<String> event ) {
            try {
                PlaceRequestEvent.fire( eventBus, tokenFormatter.toPlaceRequest( event.getValue() ), true );
            } catch ( TokenFormatException e ) {
                e.printStackTrace();
            }
        }

    }

    private final EventBus eventBus;

    private final TokenFormatter tokenFormatter;

    private final Map<Class<? extends Place>, Place> placeMap;

    public DefaultPlaceManager( EventBus eventBus, TokenFormatter tokenFormatter ) {
        this( eventBus, tokenFormatter, (Place[]) null );
    }

    public DefaultPlaceManager( EventBus eventBus, TokenFormatter tokenFormatter, Place... places ) {
        this.eventBus = eventBus;
        this.tokenFormatter = tokenFormatter;

        PlaceEventHandler handler = new PlaceEventHandler();

        // Register ourselves with the History API.
        History.addValueChangeHandler( handler );

        // Listen for manual place change events.
        eventBus.addHandler( PlaceChangedEvent.getType(), handler );

        // Listen for place revelation requests.
        eventBus.addHandler( PlaceRevealedEvent.getType(), handler );

        placeMap = new HashMap<Class<? extends Place>, Place>();

        if ( places != null ) {
            for ( Place place : places ) {
                registerPlace( place );
            }
        }
    }

    public boolean registerPlace( Place place ) {
        if ( !placeMap.containsKey( place.getClass() ) ) {
            place.addHandlers( eventBus );
            placeMap.put( place.getClass(), place );
            return true;
        }
        return false;
    }

    public boolean deregisterPlace( Place place ) {
        if ( !placeMap.containsKey( place.getClass() ) ) {
            place.removeHandlers( eventBus );
            placeMap.remove( place.getClass() );
            return true;
        }
        return false;
    }

    private void updateHistory( Place place ) {
        updateHistory( place.createRequest() );
    }

    // Updates History if it has changed, without firing another
    // 'ValueChangeEvent'.
    private void updateHistory( PlaceRequest request ) {
        try {
            String requestToken = tokenFormatter.toHistoryToken( request );
            String historyToken = History.getToken();
            if ( historyToken == null || !historyToken.equals( requestToken ) )
                History.newItem( requestToken, false );
        } catch ( TokenFormatException e ) {
            // Do nothing.
        }
    }

    /**
     * Fires a {@link PlaceRequestEvent} with the current history token, if
     * present. If no history token is set, <code>false</code> is returned.
     *
     * @return <code>true</code>
     */
    public boolean fireCurrentPlace() {
        if ( History.getToken() != null ) {
            History.fireCurrentHistoryState();
            return true;
        }
        return false;
    }
}
