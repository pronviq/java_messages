package ru.max.messages.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import ru.max.messages.dtos.MessageDTO;
import ru.max.messages.exceptions.Exceptions;
import ru.max.messages.service.MessageService;
import ru.max.messages.service.TokenService;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
  private final MessageService messageService;
  private final TokenService tokenService;
  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/sendmessage")
  // @SendTo("/topic/messages")
  public ResponseEntity<?> sendMessage(StompHeaderAccessor acc, @RequestBody MessageDTO messageDTO, Message message) {
    String accessToken = acc.getFirstNativeHeader("accessToken");
    if (accessToken == null) {
      throw Exceptions.UNAUTHORIZED;
    }
    
    // messagingTemplate.convertAndSendToUser(, accessToken, accessToken);
    var user = acc.getUser();
    if (user != null) {
      System.out.println("user name is " + user.getName());
    } else {
      System.out.println("user is null");
    }
    Long user_id = tokenService.verifyIdFromToken(accessToken);
    messageDTO.setOwnerId(user_id);

    return messageService.sendMessage(messageDTO);
  }

  // @SubscribeMapping("/topic/messages")
  // public String subscribeMessages(@RequestParam Long user_id) {
  //   System.out.println("subscribed " + user_id);
  //   return "done";
  // }
}
