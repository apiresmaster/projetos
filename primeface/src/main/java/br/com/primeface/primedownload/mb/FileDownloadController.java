package br.com.primeface.primedownload.mb;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "fileDownloadController")
@RequestScoped
public class FileDownloadController {

	private StreamedContent file;
	
	public FileDownloadController() {          
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().
        		getExternalContext().getContext()).getResourceAsStream("/images/logo.png");  
        file = new DefaultStreamedContent(stream, "image/png", "logo.png");  
    } 
	
	public StreamedContent getFile() {  
        return file;  
    } 
}
