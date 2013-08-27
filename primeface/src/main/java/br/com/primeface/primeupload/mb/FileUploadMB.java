package br.com.primeface.primeupload.mb;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "fileUploadMB")
@SessionScoped
public class FileUploadMB {

	private byte[] arquivo;

	public FileUploadMB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna o caminho da aplicação
	 * @return
	 */
	public String getPathApp(){
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
		return context.getRealPath("/pasta//");
	}

	/**
	 * Cria arquivo gravando no mesmo os dados inseridos no File
	 * @throws IOException
	 */
	public void criaArquivo() throws IOException {          
		FileOutputStream fos = new FileOutputStream(getPathApp()); 
		fos.write(this.arquivo);  
		fos.close();  
	}

	public void doUpload(FileUploadEvent event) throws IOException {

		try{
			//Cria um arquivo UploadFile, para receber o arquivo do evento
			UploadedFile arq = event.getFile();

			InputStream in = new BufferedInputStream(arq.getInputstream());

			//copiar para pasta do projeto
			File file = new File(getPathApp() + arq.getFileName());

			//O método file.getAbsolutePath() fornece o caminho do arquivo criado
			//Pode ser usado para ligar algum objeto do banco ao arquivo enviado
			String fileNameUploaded = file.getAbsolutePath();

			FileOutputStream fout = new FileOutputStream(file);

			while(in.available() != 0){

				fout.write(in.read());

			}

			fout.close();

			String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded +
					"</b><br/>"+ "Tamanho do Arquivo: <b>"+
					arq.getSize()+"</b>"+" diretório do arquivo: "+ getPathApp(); 
			FacesContext facesContext = FacesContext.getCurrentInstance(); 
			facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile));

		}

		catch(Exception ex){
			ex.printStackTrace();
		}

	}

	//	public void doUpload(FileUploadEvent fileUploadEvent) throws IOException{ 
	//		UploadedFile uploadedFile = fileUploadEvent.getFile(); 
	//		String fileNameUploaded = uploadedFile.getFileName(); 
	//		long fileSizeUploaded = uploadedFile.getSize();
	//		
	//		String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded +
	//				"</b><br/>"+ "Tamanho do Arquivo: <b>"+
	//				fileSizeUploaded+"</b>"+" diretório do arquivo: "+ getPathApp(); 
	//		FacesContext facesContext = FacesContext.getCurrentInstance(); 
	//		facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile)); 
	//		
	//		}
}
