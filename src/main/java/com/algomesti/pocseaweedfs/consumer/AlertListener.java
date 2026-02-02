package com.algomesti.pocseaweedfs.consumer;

import com.algomesti.pocseaweedfs.repository.AlertRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j // Certifique-se de que o plugin Lombok está instalado na IDE
@Component
public class AlertListener {

    // Plano B caso o Lombok falhe:
    // private static final Logger log = LoggerFactory.getLogger(AlertListener.class);

    private final AlertRepository repository;
    private final ObjectMapper objectMapper;
    private final MinioClient minioClient;

//    @Value("${minio.bucket:evidencias}")
//    private String bucketName;

    public AlertListener(AlertRepository repository, MinioClient minioClient) {
        this.repository = repository;
        this.minioClient = minioClient;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @RabbitListener(queues = "alerts.queue")
    public void process(Message message) {
//        try {
//            String rawBody = new String(message.getBody());
//            log.info(">>> [DEBUG] Conteúdo Bruto do RabbitMQ: {}", rawBody);
//
//            String json = new String(message.getBody());
//            log.info(">>> RabbitMQ: Mensagem recebida para processamento.");
//
//            AlertEntity entity = objectMapper.readValue(json, AlertEntity.class);
//            String pathNoTemp = entity.getLocalPath();
//
//            if (pathNoTemp == null) {
//            // 2. Upload para o MinIO Local
//            log.info(">>> MinIO Local: Enviando arquivo {} para o bucket {}", fileNameNoMinio, bucketName);
//            try (FileInputStream fis = new FileInputStream(fileTemp)) {
//                minioClient.putObject(
//                        PutObjectArgs.builder()
//                                .bucket(bucketName)
//                                .object(fileNameNoMinio)
//                                .stream(fis, fileSize, -1)
//                                .contentType("video/mp4")
//                                .build()
//                );
//            }
//
//            // 3. Atualizar a Entidade
////            entity.setLocalPath(fileNameNoMinio);
////            entity.setFileSize(fileSize); // Agora o CloudSyncJob saberá o tamanho sem perguntar ao MinIO!
//            entity.setStatus("PENDENTE");
//
//            repository.save(entity);
////            log.info(">>> MongoDB: Alerta salvo com referência ao MinIO: {}", fileNameNoMinio);
//
//            // 4. Limpar arquivo do /tmp
//            Path pathToDelete = Paths.get(pathNoTemp);
////            Files.delete(pathToDelete);
//            log.info(">>> Sistema: Arquivo temporário removido com sucesso: {}", pathNoTemp);
//
//        } catch (Exception e) {
//            log.error(">>> Falha Crítica no Processamento do Alerta: {}", e.getMessage());
//        }
    }
}