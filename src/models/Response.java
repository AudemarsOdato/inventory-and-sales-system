package models;

public class Response<T> {
        private T object;
        private String message;
        private int status;

        public Response(int status, T object, String message) {
                this.status = status;
                this.object = object;
                this.message = message;             
        }
        
        public Response(int status, String message) {
                this.status = status;
                this.message = message;             
        }
        
        public Response(int status, T object) {
                this.status = status;
                this.object = object;            
        }

        public T getObject() {
                return object;
        }
        
        public String getMessage() {
                return message;
        }

        public boolean ok() {
                System.out.println("response status code: "+status);
                return status == 200;
        }
}