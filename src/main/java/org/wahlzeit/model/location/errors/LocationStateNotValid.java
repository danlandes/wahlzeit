package org.wahlzeit.model.location.errors;

public class LocationStateNotValid extends RuntimeException {
    public LocationStateNotValid(final String message) {
        super(message);
    }

    public static class CoordinateWasNull extends LocationStateNotValid {
        public CoordinateWasNull() {
            super("Argument 'coordinate' of constructor location was null. This state is not valid!");
        }
    }

    public static class CoordinateIsNotSupportedSubtype extends LocationStateNotValid {
        public CoordinateIsNotSupportedSubtype() {
            super("Argument 'coordinate' of constructor location is not supported." +
                    " To support a new supertype you have to modify e.g the database. Follow the Docs for more infos!");
        }
    }
}