package in.ashwanthkumar.gocd.artifacts.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Function;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class JanitorConfiguration {
    private String server;
    private String artifactStorage;
    private List<PipelineConfig> pipelines;

    public static JanitorConfiguration load(String file) {
        return load(ConfigFactory.parseFile(new File(file)));
    }

    public static JanitorConfiguration load(Config config) {
        config = config.getConfig("gocd.cleanup");
        List<PipelineConfig> pipelines = Lists.map((List<Config>) config.getConfigList("pipelines"), new Function<Config, PipelineConfig>() {
            @Override
            public PipelineConfig apply(Config config) {
                return PipelineConfig.fromConfig(config);
            }
        });

        return new JanitorConfiguration()
                .setServer(config.getString("server"))
                .setArtifactStorage(config.getString("artifacts-dir"))
                .setPipelines(pipelines);
    }

    public String getServer() {
        return server;
    }

    public JanitorConfiguration setServer(String server) {
        this.server = server;
        return this;
    }

    public String getArtifactStorage() {
        return artifactStorage;
    }

    public JanitorConfiguration setArtifactStorage(String artifactStorage) {
        this.artifactStorage = artifactStorage;
        return this;
    }

    public List<PipelineConfig> getPipelines() {
        return pipelines;
    }

    public JanitorConfiguration setPipelines(List<PipelineConfig> pipelines) {
        this.pipelines = pipelines;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JanitorConfiguration that = (JanitorConfiguration) o;
        return Objects.equals(server, that.server) &&
                Objects.equals(artifactStorage, that.artifactStorage) &&
                Objects.equals(pipelines, that.pipelines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, artifactStorage, pipelines);
    }
}
