package ejbs;


public interface Hello_SEI extends java.rmi.Remote
{
  public java.lang.String say(java.lang.String name) throws java.rmi.RemoteException;
}