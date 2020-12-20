package org.wahlzeit.model.location.errors;

public class CoordinateStateNotValid extends RuntimeException {
    public CoordinateStateNotValid(final String message) {
        super(message);
    }

    public static class CoordinateCreationFailed extends CoordinateStateNotValid {
        public CoordinateCreationFailed() {
            super("Could not create instance of coordinate");
        }
        public static class OfInstanceCartesian extends CoordinateCreationFailed {
        }

        public static class OfInstanceSpheric extends CoordinateCreationFailed {
        }
    }
}
