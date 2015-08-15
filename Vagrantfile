VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.box = "ubuntu/trusty64"
  config.vm.hostname = "vagrant.example.com"

  config.vm.network "forwarded_port", guest: 7770, host: 7769
  config.vm.network "forwarded_port", guest: 9990, host: 9989
  config.vm.network "forwarded_port", guest: 80, host: 8080

  config.vm.synced_folder "~/.ivy2/cache/", "/root/.ivy2/cache", create: true
  config.vm.synced_folder "~/.ivy2/local/", "/root/.ivy2/local", create: true

  config.vm.provision "ansible" do |ansible|
    ansible.playbook = "ansible/site.yml"
    ansible.inventory_path = "ansible/inventories/vagrant/inventory"
    # ansible.tags = "memento-finatra"
  end

  config.vm.provider "virtualbox" do |v|
    v.cpus = 2
    v.memory = 2048
    v.name = "vagrant"
    v.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    v.customize ["modifyvm", :id, "--natdnsproxy1", "on"]
  end

  if Vagrant.has_plugin?("vagrant-cachier")
    config.cache.scope = :box
  end

end
