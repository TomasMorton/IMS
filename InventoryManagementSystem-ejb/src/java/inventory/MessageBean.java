/**
   A class that represents a message-driven bean that receives JMS
   messages from a queue
   In order to deploy this example on an application server a JMS
   Connection Factory resource with JNDI name "jms/ConnectionFactory"
   and a JMS Destination Queue resource with JNDI name "jms/Queue"
   (with a Physical Destination Name such as Queue) must first be
   created on the application server.
   Note that if this is deployed on the server then MessageReceiver
   cannot also connect to the Queue as a receiver.
   @see messagesender.MessageSender.java
*/
package inventory;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="InventoryQueue")
   public class MessageBean implements MessageListener
{
   // field obtained via dependency injection (not used here)
   @Resource private MessageDrivenContext context;

   public MessageBean()
   {}

   @Override
   public void onMessage(Message message)
   {  try
      {  if (message instanceof TextMessage)
            System.out.println("MessageBean received text message: "
               + ((TextMessage)message).getText());
         else
            System.out.println
               ("MessageBean received non-text message: " + message);
      }
      catch(JMSException e)
      {  System.err.println("Exception with incoming message: "+e);
      }
   }
}
