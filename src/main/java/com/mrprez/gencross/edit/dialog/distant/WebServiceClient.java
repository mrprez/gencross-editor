package com.mrprez.gencross.edit.dialog.distant;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import com.mrprez.gencross.Version;
import com.mrprez.gencross.disk.PluginDescriptor;

public class WebServiceClient {
	private static String NAMESPACE_URI = "http://soapinterop.org/";
	private static String GET_PLUGIN_LIST = "getPluginList";
	
	private Service service = new Service();
	
	private URL targetEndpointUrl;
	
	
	public WebServiceClient(String targetEndpointAddress) throws MalformedURLException {
		super();
		this.targetEndpointUrl = new URL(targetEndpointAddress);
	}

	public Collection<PluginDescriptor> loadPluginList() throws ServiceException, RemoteException{
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(targetEndpointUrl);
		call.setOperationName(new QName(NAMESPACE_URI, GET_PLUGIN_LIST));
		QName pluginDescriptorXmlType = new QName("urn:PluginDescriptor", "local");
		call.registerTypeMapping(PluginDescriptor.class, pluginDescriptorXmlType, new BeanSerializerFactory(PluginDescriptor.class, pluginDescriptorXmlType), new BeanDeserializerFactory(PluginDescriptor.class, pluginDescriptorXmlType));
		QName versionXmlType = new QName("urn:Version", "local");
		call.registerTypeMapping(PluginDescriptor.class, versionXmlType, new BeanSerializerFactory(Version.class, versionXmlType), new BeanDeserializerFactory(Version.class, versionXmlType));
		
		Object pluginDescriptorTab[] = (Object[]) call.invoke(new Object[0]);
		
		Collection<PluginDescriptor> pluginList = new ArrayList<PluginDescriptor>();
		for(int i=0; i<pluginDescriptorTab.length; i++){
			pluginList.add((PluginDescriptor) pluginDescriptorTab[i]);
		}
		
		return pluginList;
	}
	

}
