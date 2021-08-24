package me.crypnotic.neutron.api.configuration;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;
import me.crypnotic.neutron.api.serializer.ComponentSerializer;
import me.crypnotic.neutron.util.FileHelper;
import net.kyori.adventure.text.Component;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializerCollection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Configuration {

    private final File folder;
    private final File file;
    private final ConfigurationLoader<?> loader;
    private ConfigurationNode node;

    public Configuration(File folder, File file, ConfigurationLoader<?> loader, ConfigurationNode node) {
        this.folder = folder;
        this.file = file;
        this.loader = loader;
        this.node = node;
    }

    public boolean reload() {
        try {
            this.node = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean save() {
        try {
            loader.save(node);

            return true;
        } catch (IOException exception) {
            exception.printStackTrace();

            return false;
        }
    }

    public ConfigurationNode getNode(Object... values) {
        return node.getNode(values);
    }

    public void setNode(ConfigurationNode node) {
        setNode(node, false);
    }

    public void setNode(ConfigurationNode node, boolean save) {
        this.node = node;

        if (save) {
            save();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public File getFolder() {
        return this.folder;
    }

    public File getFile() {
        return this.file;
    }

    @SuppressWarnings("squid:S1452")
    public ConfigurationLoader<?> getLoader() {
        return this.loader;
    }

    public static class Builder {
        @SuppressWarnings("UnstableApiUsage")
        private static final TypeSerializerCollection TYPE_SERIALIZERS = TypeSerializerCollection.defaults()
                .newChild()
                .register(TypeToken.of(Component.class), new ComponentSerializer());

        private Path folder;
        private String name;

        public Configuration build() {
            Preconditions.checkNotNull(folder);
            Preconditions.checkNotNull(name);

            try {
                File file = FileHelper.getOrCreate(folder, name);
                ConfigurationLoader<?> loader = HoconConfigurationLoader.builder()
                        .setDefaultOptions(ConfigurationOptions.defaults().withSerializers(TYPE_SERIALIZERS))
                        .setFile(file)
                        .build();

                ConfigurationNode node = loader.load();

                return new Configuration(folder.toFile(), file, loader, node);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            return null;
        }

        public Builder folder(Path folder) {
            this.folder = folder;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
    }
}
