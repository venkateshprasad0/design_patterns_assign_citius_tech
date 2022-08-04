package app;

public class CustomException {

	class InvalidSeatNumberException extends RuntimeException {
		public InvalidSeatNumberException(String message) {
			super(message);
		}
	}

	class SeatsNotAvailableException extends RuntimeException {

		public SeatsNotAvailableException(String message) {
			super(message);
		}

	}

	class UnknownShowException extends RuntimeException {

		public UnknownShowException(String message) {
			super(message);
		}

	}
}
