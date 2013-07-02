require 'buildr/git_auto_version'
require 'buildr/top_level_generate_dir'

desc 'MMVP: Enhance the activities and places library'
define 'gwt-mmvp' do
  project.group = 'org.realityforge.gwt.ga'
  compile.options.source = '1.6'
  compile.options.target = '1.6'
  compile.options.lint = 'all'

  project.version = ENV['PRODUCT_VERSION'] if ENV['PRODUCT_VERSION']

  compile.with :javax_annotation,
               :gwt_user,
               :gwt_gin,
               :javax_inject,
               :google_guice

  package(:jar).tap do |jar|
    jar.include compile.sources, :as => '.'
  end

  iml.add_jruby_facet
end
