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

    public static class CalculationError extends  CoordinateStateNotValid {
        public CalculationError(final String message) {
            super(message);
        }
        public static class ZeroValueForbidden extends CalculationError {
            public ZeroValueForbidden() {
                super("Value zero is forbidden here!");
            }
        }
        public static class NotAValue extends CalculationError {
            public NotAValue() {
                super("Not a Value!");
            }
        }
        public static class NegativeValueForbidden extends CalculationError {
            public NegativeValueForbidden() {
                super("Negative value is forbidden here!");
            }
        }
        public static class InfinityValueForbidden extends CalculationError {
            public InfinityValueForbidden() {
                super("Negative value is forbidden here!");
            }
        }
    }
}
