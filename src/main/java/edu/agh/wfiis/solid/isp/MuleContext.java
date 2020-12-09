package edu.agh.wfiis.solid.isp;

import org.mule.DataTypeConversionResolver;
import org.mule.api.Injector;
import org.mule.api.SingleResourceTransactionFactoryManager;
import org.mule.api.client.LocalMuleClient;
import org.mule.api.config.MuleConfiguration;
import org.mule.api.config.ThreadingProfile;
import org.mule.api.context.WorkManager;
import org.mule.api.context.notification.FlowTraceManager;
import org.mule.api.context.notification.ServerNotification;
import org.mule.api.context.notification.ServerNotificationListener;
import org.mule.api.el.ExpressionLanguage;
import org.mule.api.endpoint.EndpointFactory;
import org.mule.api.exception.MessagingExceptionHandler;
import org.mule.api.exception.RollbackSourceCallback;
import org.mule.api.exception.SystemExceptionHandler;
import org.mule.api.execution.ExceptionContextProvider;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Lifecycle;
import org.mule.api.lifecycle.LifecycleManager;
import org.mule.api.registry.MuleRegistry;
import org.mule.api.registry.RegistrationException;
import org.mule.api.registry.Registry;
import org.mule.api.security.SecurityManager;
import org.mule.api.serialization.ObjectSerializer;
import org.mule.api.store.ListableObjectStore;
import org.mule.api.store.ObjectStoreManager;
import org.mule.api.util.StreamCloserService;
import org.mule.config.bootstrap.ArtifactType;
import org.mule.context.notification.NotificationException;
import org.mule.context.notification.ServerNotificationManager;
import org.mule.extension.ExtensionManager;
import org.mule.management.stats.AllStatistics;
import org.mule.management.stats.ProcessingTimeWatcher;
import org.mule.util.lock.LockFactory;
import org.mule.util.queue.QueueManager;

import javax.resource.spi.work.WorkListener;
import javax.transaction.TransactionManager;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MuleContext extends Lifecycle
{
   @Deprecated
   void setTransactionManager(TransactionManager manager) throws Exception;

   TransactionManager getTransactionManager();

   ServerNotificationManager getNotificationManager();

   /**
    * Registers an intenal server event listener. The listener will be notified
    * when a particular event happens within the server. Typically this is not
    * an event in the same sense as an MuleEvent (although there is nothing
    * stopping the implementation of this class triggering listeners when a
    * MuleEvent is received).
    * <p/>
    * The types of notifications fired is entirely defined by the implementation of
    * this class
    *
    * @param l the listener to register
    */
   void registerListener(ServerNotificationListener l) throws NotificationException;

   /**
    * Registers an intenal server event listener. The listener will be notified
    * when a particular event happens within the server. Typically this is not
    * an event in the same sense as an MuleEvent (although there is nothing
    * stopping the implementation of this class triggering listeners when a
    * MuleEvent is received).
    * <p/>
    * The types of notifications fired is entirely defined by the implementation of
    * this class
    *
    * @param l                  the listener to register
    * @param resourceIdentifier a particular resource name for the given type
    *                           of listener For example, the resourceName could be the name of
    *                           a service if the listener was a ServiceNotificationListener
    */
   void registerListener(ServerNotificationListener l, String resourceIdentifier) throws NotificationException;

   /**
    * Unregisters a previously registered listener. If the listener has not
    * already been registered, this method should return without exception
    *
    * @param l the listener to unregister
    */
   void unregisterListener(ServerNotificationListener l);

   /**
    * Fires a server notification to all regiistered listeners
    *
    * @param notification the notification to fire
    */
   void fireNotification(ServerNotification notification);


   WorkListener getWorkListener();


   ObjectStoreManager getObjectStoreManager();

   ExtensionManager getExtensionManager();

   /**
    * The instance of {@link org.mule.api.serialization.ObjectSerializer}
    * to be used to serialize/deserealize objects
    *
    * @return a {@link org.mule.api.serialization.ObjectSerializer}
    * @since 3.7.0
    */
   ObjectSerializer getObjectSerializer();

   AllStatistics getStatistics();

   LifecycleManager getLifecycleManager();

   MuleRegistry getRegistry();

   /**
    * Returns a {@link Injector} capable of
    * injecting dependencies into objects
    *
    * @return a {@link Injector}
    * @since 3.7.0
    */
   Injector getInjector();

   MuleConfiguration getConfiguration();


   /**
    * Returns the configured {@link org.mule.api.util.StreamCloserService}
    *
    * @return a {@link org.mule.api.util.StreamCloserService}
    * @since 3.5.0
    */
   public StreamCloserService getStreamCloserService();

   /**
    * @deprecated as of 3.7.0. This will be removed in Mule 4.0
    */
   @Deprecated
   void addRegistry(Registry registry);

   /**
    * @deprecated as of 3.7.0. This will be removed in Mule 4.0
    */
   @Deprecated
   void removeRegistry(Registry registry);

   /**
    * Returns the Expression Manager configured for this instance of Mule
    * @return the Expression Manager configured for this instance of Mule
    * @see org.mule.api.expression.ExpressionManager
    */
   ExpressionManager getExpressionManager();

   /**
    * Returns the EndpointFactory configured for this instance of Mule
    * @return the EndpointFactory configured for this instance of Mule
    * @see EndpointFactory
    */
   EndpointFactory getEndpointFactory();

   void setExecutionClassLoader(ClassLoader cl);

   ClassLoader getExecutionClassLoader();

   LocalMuleClient getClient();

   SystemExceptionHandler getExceptionListener();

   void setExceptionListener(SystemExceptionHandler exceptionListener);

   void setObjectStore(String name, ListableObjectStore<Serializable> store) throws RegistrationException;

   void handleException(Exception e, RollbackSourceCallback rollbackMethod);

   void handleException(Exception e);

   /**
    * @return the ID of the cluster the current instance belongs to.  Returns the empty string if this instance
    * isn't part of a cluster.
    */
   String getClusterId();

   /**
    * @return the cluster node ID for the current instance belongs to.  Returns 0 if this instance
    * isn't part of a cluster.
    */
   int getClusterNodeId();

   /**
    * @return true if this instance in the designated poller.  This will always be true unless the instance is part of
    * a cluster.
    */
   boolean isPrimaryPollingInstance();

   /**
    * Generate a unique ID string; this will begin with the cluster node ID followed by a
    * dash, e.g. "3-XXXYYY"
    */
   String getUniqueIdString();

   /**
    * Return all annotations seen in the configuration
    */
   Map<QName, Set<Object>> getConfigurationAnnotations();

   /**
    * @return default exception strategy. If no default exception strategy was configured it returns {@link org.mule.exception.DefaultMessagingExceptionStrategy}
    */
   MessagingExceptionHandler getDefaultExceptionStrategy();

   /**
    * @return single resource transaction factory manager. Used to retrieve a transaction factory for each transactional resource (i.e jdbc DataSource, jms Connection)
    */
   SingleResourceTransactionFactoryManager getTransactionFactoryManager();

   /**
    * @return a non null {@link org.mule.DataTypeConversionResolver} instance to resolve implicit data type conversions
    */
   DataTypeConversionResolver getDataTypeConverterResolver();

   /**
    * Expression Language for evaluating expressions using Mule as the context
    * @return
    */
   ExpressionLanguage getExpressionLanguage();

   /**
    * Factory for creating locks for synchronizing mule components.
    *
    * Synchronization must be done using LockFactory locks in order for mule components to work in single servers as in a cluster
    *
    * @return a factory for creating locks
    */
   LockFactory getLockFactory();

   /**
    * @return {@link {ProcessingTimeWatcher} used to compute processing time of finalized events
    */
   ProcessingTimeWatcher getProcessorTimeWatcher();

   /**
    * Makes the caller wait until the {@link org.mule.api.MuleContext} was started
    *
    * @param timeout maximum number of milliseconds that will be waiting
    * @return true if the context started before the timeout, false otherwise
    * @throws InterruptedException if the current thread is interrupted while waiting
    */
   boolean waitUntilStarted(int timeout) throws InterruptedException;

   /**
    * The {@link ArtifactType} indicating if this configuration object is for an application or a domain.
    */
   ArtifactType getArtifactType();

   /**
    * @return the callbacks for notifying when a flow call from another flow is started or completed.
    *
    * @since 3.8.0
    */
   FlowTraceManager getFlowTraceManager();

   /**
    * @return the providers for additional context information for exceptions.
    *
    * @since 3.8.0
    */
   Collection<ExceptionContextProvider> getExceptionContextProviders();

}

