

クラスターにデプロイされている時はこんな感じで、クラスターメンバーが稼働するnodagentのブートストラップポート
```
	@EJB(name="ejb/Hello")
    private HelloRemote hello = null;




		HttpSession session = request.getSession(true);
		HelloRemote hello = (HelloRemote)session.getAttribute("bean");
		if (hello!=null) {
			hello.say();
		} else {
			InitialContext initialContext = null;
			try{
				Hashtable table = new Hashtable();
				table.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
				table.put(Context.PROVIDER_URL,"corbaloc::c96912v1.fyre.ibm.com:9812,:c96912v1.fyre.ibm.com:9813");
				initialContext = new InitialContext(table);
				java.lang.Object o = initialContext.lookup("ejb/SimpleEJB3/SimpleEJB3EJB.jar/Hello#simple.ejb3.view.HelloRemote");
				hello = (HelloRemote)PortableRemoteObject.narrow(o, HelloRemote.class);
				session.setAttribute("bean", hello);
			}catch(Exception e){
				e.printStackTrace();
			}
  }
```

バインドファイルはこんな感じ

	<ejb-ref name="ejb/Hello" binding-name="corbaname:iiop:c96912v1.fyre.ibm.com:9812,:c96912v1.fyre.ibm.com:9813/NameServiceServerRoot#ejb/SimpleEJB3/SimpleEJB3EJB.jar/Hello#simple\.ejb3\.view\.HelloRemote" />
