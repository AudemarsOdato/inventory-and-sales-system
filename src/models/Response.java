package models;

public class Response<T> {
        private T object;
        private String message;
        private int status;

        public Response(int status, T object, String message) {
                this.object = object;
                this.message = message;             
        }

        public Response(int status, String message) {
                this.message = message;             
        }

        public Response(int status, T object) {
                this.object = object;            
        }

        public T getObject() {
                return object;
        }
        
        public String getMessage() {
                return message;
        }

        public boolean ok() {
                return status == 200;
        }
}