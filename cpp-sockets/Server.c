#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#define PORT 8080

int main (int argc, char const* argv[]){
    int server_fd, new_socket, valread;
    strcut sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024]= {0};
    char *hello = "Hello from server";

    if((server_fd =socket(AF_INET,SOCK_STREAM,0))<0){
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    if(setsockopt(server_fd,SOL_SOCKET,SO_REUSEADDR|SO_REUSEPORT,&opt,sizeof(opt))){
        // per
    }
}