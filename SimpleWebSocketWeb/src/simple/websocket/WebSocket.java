package simple.websocket;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/WebSocket")
public class WebSocket
{
  public static List<Session> sessions = new ArrayList();
  
  @OnOpen
  public void onOpen(Session session, EndpointConfig ef)
  {
    System.out.println("open!");
    sessions.add(session);
  }
  
  @OnMessage
  public void receiveMessage(String name)
  {
    try
    {
      for (Session session : sessions) {
        session.getBasicRemote().sendText(name);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  @OnClose
  public void onClose(Session session, CloseReason reason)
  {
    System.out.println("close!");
  }
  
  @OnError
  public void onError(Throwable t)
  {
    t.printStackTrace();
  }
}
