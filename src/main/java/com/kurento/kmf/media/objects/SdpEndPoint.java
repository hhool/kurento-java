package com.kurento.kmf.media.objects;

import com.kurento.kmf.media.internal.refs.MediaElementRefDTO;

//// TODO: update doc
///**
// * A NetworkConnection is a {@link zMediaElement} that drives network media
// * ports.<br>
// * <p>
// * A NetworkConnection can be created with
// * {@link MediaSession#createNetworkConnection(Parameters)}<br>
// * Example:<br>
// * <code>NetworkConnection myNC = myMediaSession.createNetworkConnection();</code>
// * 
// * It handles a set of media ports, defined by a {@link SessionSpec}.
// * <p>
// * A NetworkConnection can handle multiple streams (audio, video), each of them
// * described by an {@link MediaSession} description.
// * <p>
// * 
// * <pre>
// *  --------------------------------------------------------------------------------
// *  -                                           stream(media description) <-rtp--->-
// *  -   NetworkConnection(session description)  stream(media description) <-rtp--->-
// *  -                                           ....                               -
// *  --------------------------------------------------------------------------------
// * </pre>
// * 
// * Actually two session descriptions are needed:
// * <ul>
// * <li>A <b>Media</b> Server session description describes the mediaserver-local
// * side of a media connection (what the media server accepts to receive)
// * <li>A <b>User Agent</b> session description, describing the remote side (for
// * example a SIPPhone)
// * 
// * 
// * <pre>
// * UserAgent                             Media Server
// *    ----                                 ---------
// *    -  -                                 -       -
// *    -  -        <-------RTP------>       -       -
// *    ----                                 ---------
// * </pre>
// * 
// * The NetworkConnection is compatible with the offer/answer model.
// * <p>
// * The Relationship with SIP signaling messages is described below:
// * 
// * <pre>
// *  A) incoming INVITE with SDP offer:
// *   UserAgent                        Application             NetworkConnection
// *   =============================================================================
// *       ------INVITE----------------->
// *          +userAgentSDP
// *                                     ---------------------->processSdpOffer(userAgentSDP)
// *                                                                     ...................>(media server)
// *                                                                     <...................
// *                                     <-------- Event ----------------
// *                                         +getMediaServerSdp()
// *       <-------- 200 OK -------------
// *              +mediaServerSDP
// * 
// *       --------- ACK -------------->
// * 
// * 
// *  B) incoming INVITE without SDP:
// *   UserAgent                        Application             NetworkConnection
// *   =============================================================================
// *       ------INVITE----------------->
// *                                     ---------------------->generateSDPOffer()
// *                                                                     ...................>(media server)
// *                                                                     <...................
// *                                     <-------- Event ----------------
// *                                         +getMediaServerSdp()
// *       <-------- 200 OK -------------
// *              +mediaServerSDP
// * 
// *       --------- ACK -------------->
// *              +userAgentSDP
// *                                    ------------------------>processSdpAnswer(userAgentSDP)
// * 
// * 
// *  C) outgoing INVITE with SDP offer
// *   UserAgent                        Application             NetworkConnection
// *   =============================================================================
// *                                     ---------------------->generateSDPOffer()
// *                                                                     ...................>(media server)
// *                                                                     <...................
// *                                     <-------- Event ----------------
// *                                         +getMediaServerSdp()
// *       <------INVITE-----------------
// *          +mediaServerSDP
// * 
// *       --------- 200 OK ------------>
// *              +userAgentSDP
// *                                    ------------------------>processSdpAnswer(userAgentSDP)
// *                                                                     ...................>(media server)
// *                                                                     <...................
// *                                     <-------- Event ----------------
// *       <--------- ACK --------------
// *  
// *  D) outgoing INVITE without SDP
// *   UserAgent                        Application             NetworkConnection
// *   =============================================================================
// *       <------INVITE-----------------
// * 
// *       --------- 200 OK ------------>
// *              +userAgentSDP
// *                                     ---------------------->processSdpOffer(userAgentSDP)
// *                                                                     ...................>(media server)
// *                                                                     <...................
// *                                     <-------- Event ----------------
// *                                         +getMediaServerSdp()
// *       <--------- ACK --------------
// *            +mediaServerSDP
// * </pre>
// * 
// * <p>
// * (this is provided as a help in understanding, but the NetworkConnections has
// * no dependency on any signaling protocol, including SIP)
// * </p>
// * </ul>
// */
public abstract class SdpEndPoint extends EndPoint {

	SdpEndPoint(MediaElementRefDTO endpointRef) {
		super(endpointRef);
	}

	// static <T extends zSdpEndPoint> SdpEndPointType getType(Class<T> type) {
	// try {
	// Field field = type.getDeclaredField(SDP_END_POINT_TYPE_FIELD_NAME);
	// return (SdpEndPointType) field.get(type);
	// } catch (NoSuchFieldException e) {
	// throw new IllegalArgumentException(e);
	// } catch (SecurityException e) {
	// throw new IllegalArgumentException(e);
	// } catch (IllegalArgumentException e) {
	// throw new IllegalArgumentException(e);
	// } catch (IllegalAccessException e) {
	// throw new IllegalArgumentException(e);
	// }
	// }
	//
	// /* SYNC */
	//
	// public String generateOffer() throws IOException {
	// MediaServerService.Client service = mssm.getMediaServerService();
	//
	// try {
	// return service.generateOffer(mediaObjectId);
	// } catch (MediaObjectNotFoundException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (MediaServerException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerService(service);
	// }
	// }
	//
	// public String processOffer(String offer) throws IOException {
	// MediaServerService.Client service = mssm.getMediaServerService();
	//
	// try {
	// return service.processOffer(mediaObjectId, offer);
	// } catch (MediaObjectNotFoundException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (MediaServerException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerService(service);
	// }
	// }
	//
	// public String processAnswer(String answer) throws IOException {
	// MediaServerService.Client service = mssm.getMediaServerService();
	//
	// try {
	// return service.processAnswer(mediaObjectId, answer);
	// } catch (MediaObjectNotFoundException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (MediaServerException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerService(service);
	// }
	// }
	//
	// /**
	// * This method gives access to the SessionSpec offered by this
	// * NetworkConnection
	// *
	// * <p>
	// * <b>Note:</b> This method returns the local MediaSpec, negotiated or
	// not.
	// * If no offer has been generated yet, it returns null. It an offer has
	// been
	// * generated it returns the offer and if an asnwer has been processed it
	// * returns the negotiated local SessionSpec.
	// * </p>
	// *
	// * @return The last agreed SessionSpec
	// * @throws IOException
	// */
	// public String getLocalSessionDescriptor() throws IOException {
	// MediaServerService.Client service = mssm.getMediaServerService();
	//
	// try {
	// return service.getLocalSessionDescription(mediaObjectId);
	// } catch (MediaObjectNotFoundException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (MediaServerException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerService(service);
	// }
	// }
	//
	// /**
	// * This method gives access to the remote session description.
	// *
	// * <p>
	// * <b>Note:</b> This method returns the media previously agreed after a
	// * complete offer-answer exchange. If no media has been agreed yet, it
	// * returns null.
	// * </p>
	// *
	// * @return The last agreed User Agent session description
	// */
	// public String getRemoteSessionDescriptor() throws IOException {
	// MediaServerService.Client service = mssm.getMediaServerService();
	//
	// try {
	// return service.getRemoteSessionDescription(mediaObjectId);
	// } catch (MediaObjectNotFoundException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (MediaServerException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerService(service);
	// }
	// }
	//
	// /* ASYNC */
	//
	// /**
	// * Request a SessionSpec offer.
	// *
	// * <p>
	// * The resulting offer is available with
	// * {@link zSdpEndPoint#getSessionSpec()}
	// * </p>
	// *
	// * <p>
	// * This can be used to initiate a connection.
	// * </p>
	// *
	// * @param cont
	// * Continuation object to notify when operation completes
	// * @throws zMediaException
	// */
	// public void generateOffer(final Continuation<String> cont)
	// throws IOException {
	// MediaServerService.AsyncClient service = mssm
	// .getMediaServerServiceAsync();
	//
	// try {
	// service.generateOffer(
	// mediaObjectId,
	// new
	// AsyncMethodCallback<MediaServerService.AsyncClient.generateOffer_call>()
	// {
	// @Override
	// public void onComplete(generateOffer_call response) {
	// try {
	// String sessionDescriptor = response.getResult();
	// cont.onSuccess(sessionDescriptor);
	// } catch (MediaObjectNotFoundException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (MediaServerException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (TException e) {
	// cont.onError(new IOException(e.getMessage(), e));
	// }
	// }
	//
	// @Override
	// public void onError(Exception exception) {
	// cont.onError(exception);
	// }
	// });
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerServiceAsync(service);
	// }
	// }
	//
	// /**
	// * Request the NetworkConnection to process the given SessionSpec offer
	// * (from the remote User Agent).<br>
	// * The resulting answer is available with
	// * {@link zSdpEndPoint#getSessionSpec()} and the remote offer will be
	// * returned by {@link zSdpEndPoint#getRemoteSessionSpec()}
	// *
	// * @param offer
	// * SessionSpec offer from the remote User Agent
	// * @param cont
	// * Continuation object to notify when operation completes and to
	// * provide the answer SessionSpec.
	// */
	// public void processOffer(String offer, final Continuation<String> cont)
	// throws IOException {
	// MediaServerService.AsyncClient service = mssm
	// .getMediaServerServiceAsync();
	//
	// try {
	// service.processOffer(
	// mediaObjectId,
	// offer,
	// new
	// AsyncMethodCallback<MediaServerService.AsyncClient.processOffer_call>() {
	// @Override
	// public void onComplete(processOffer_call response) {
	// try {
	// String sessionDescriptor = response.getResult();
	// cont.onSuccess(sessionDescriptor);
	// } catch (MediaObjectNotFoundException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (NegotiationException e) {
	// cont.onError(new zMediaException(
	// e.getMessage(), e));
	// } catch (MediaServerException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (TException e) {
	// cont.onError(new IOException(e.getMessage(), e));
	// }
	// }
	//
	// @Override
	// public void onError(Exception exception) {
	// cont.onError(exception);
	// }
	// });
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerServiceAsync(service);
	// }
	// }
	//
	// /**
	// * Request the NetworkConnection to process the given SessionSpec answer
	// * (from the remote User Agent).<br>
	// * The answer become available on method
	// * {@link zSdpEndPoint#getRemoteSessionSpec()}
	// *
	// * @param answer
	// * SessionSpec answer from the remote User Agent
	// * @param cont
	// * Continuation object to notify when operation completes,
	// * returned SessionSpec is the local SessionSpec.
	// */
	// public void processAnswer(String answer, final Continuation<String> cont)
	// throws IOException {
	// MediaServerService.AsyncClient service = mssm
	// .getMediaServerServiceAsync();
	//
	// try {
	// service.processAnswer(
	// mediaObjectId,
	// answer,
	// new
	// AsyncMethodCallback<MediaServerService.AsyncClient.processAnswer_call>()
	// {
	// @Override
	// public void onComplete(processAnswer_call response) {
	// try {
	// String sessionDescriptor = response.getResult();
	// cont.onSuccess(sessionDescriptor);
	// } catch (MediaObjectNotFoundException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (NegotiationException e) {
	// cont.onError(new zMediaException(
	// e.getMessage(), e));
	// } catch (MediaServerException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (TException e) {
	// cont.onError(new IOException(e.getMessage(), e));
	// }
	// }
	//
	// @Override
	// public void onError(Exception exception) {
	// cont.onError(exception);
	// }
	// });
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerServiceAsync(service);
	// }
	// }
	//
	// /**
	// * This method gives access to the SessionSpec offered by this
	// * NetworkConnection
	// *
	// * <p>
	// * <b>Note:</b> This method returns the local MediaSpec, negotiated or
	// not.
	// * If no offer has been generated yet, it returns null. It an offer has
	// been
	// * generated it returns the offer and if an asnwer has been processed it
	// * returns the negotiated local SessionSpec.
	// * </p>
	// *
	// * @return The last agreed SessionSpec
	// * @throws IOException
	// */
	// public void getLocalSessionDescriptor(final Continuation<String> cont)
	// throws IOException {
	// MediaServerService.AsyncClient service = mssm
	// .getMediaServerServiceAsync();
	//
	// try {
	// service.getLocalSessionDescription(
	// mediaObjectId,
	// new
	// AsyncMethodCallback<MediaServerService.AsyncClient.getLocalSessionDescription_call>()
	// {
	// @Override
	// public void onComplete(
	// getLocalSessionDescription_call response) {
	// try {
	// String sessionDescriptor = response.getResult();
	// cont.onSuccess(sessionDescriptor);
	// } catch (MediaObjectNotFoundException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (MediaServerException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (TException e) {
	// cont.onError(new IOException(e.getMessage(), e));
	// }
	// }
	//
	// @Override
	// public void onError(Exception exception) {
	// cont.onError(exception);
	// }
	// });
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerServiceAsync(service);
	// }
	// }
	//
	// /**
	// * This method gives access to the remote session description.
	// *
	// * <p>
	// * <b>Note:</b> This method returns the media previously agreed after a
	// * complete offer-answer exchange. If no media has been agreed yet, it
	// * returns null.
	// * </p>
	// *
	// * @return The last agreed User Agent session description
	// */
	// public void getRemoteSessionDescriptor(final Continuation<String> cont)
	// throws IOException {
	// MediaServerService.AsyncClient service = mssm
	// .getMediaServerServiceAsync();
	//
	// try {
	// service.getRemoteSessionDescription(
	// mediaObjectId,
	// new
	// AsyncMethodCallback<MediaServerService.AsyncClient.getRemoteSessionDescription_call>()
	// {
	// @Override
	// public void onComplete(
	// getRemoteSessionDescription_call response) {
	// try {
	// String sessionDescriptor = response.getResult();
	// cont.onSuccess(sessionDescriptor);
	// } catch (MediaObjectNotFoundException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (MediaServerException e) {
	// cont.onError(new RuntimeException(e
	// .getMessage(), e));
	// } catch (TException e) {
	// cont.onError(new IOException(e.getMessage(), e));
	// }
	// }
	//
	// @Override
	// public void onError(Exception exception) {
	// cont.onError(exception);
	// }
	// });
	// } catch (TException e) {
	// throw new IOException(e.getMessage(), e);
	// } finally {
	// mssm.releaseMediaServerServiceAsync(service);
	// }
	// }
	//
}
